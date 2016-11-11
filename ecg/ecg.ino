/*******
Firmware para el dispositivo de monitorización del ECG
Borja Bordel - Proyecto Sémola
*******/
#include <SoftwareSerial.h>

#define ANALOG_PIN   A2
#define PIN_DISPLAY_DATA 4
#define PIN_DISPLAY_CLOCK 5
#define PIN_DISPLAY_LATCH 3
#define PIN_ALARMA_0 9
#define PIN_ALARMA_1 10
#define PIN_RX_BT 11
#define PIN_TX_BT 12

#define POS_NIVEL 15
#define POS_ALARM 23
#define POS_START 6

#define ESPERA_MS  10

#define NUM_MUESTRAS 17
#define LONG_RESPONSE_CHAR 26
#define LONG_START_CHAR 9
#define SPEED_BT 115200

#define ID "05"
#define MAX_VALUE "1023"
#define MIN_VALUE "0000"

int muestras [NUM_MUESTRAS];
int contador;
char mensajeRX [LONG_RESPONSE_CHAR];
char startRX [LONG_START_CHAR];
SoftwareSerial serialBT (PIN_RX_BT, PIN_TX_BT);

boolean started;

void rx_start() {
  for (int j = 0; j < LONG_START_CHAR; j++) {
       startRX[j] = serialBT.read();
       delay(1);
  }
}

void rx_message () {  
     for (int j = 0; j < LONG_RESPONSE_CHAR; j++) {
       mensajeRX[j] = serialBT.read();
       delay(1);
    }
}

void alarma (int num) {
   switch (num) {
      case 0:
        digitalWrite(PIN_ALARMA_0, LOW);
        digitalWrite(PIN_ALARMA_1, LOW);
        break;
      case 1:
        digitalWrite(PIN_ALARMA_0, LOW);
        digitalWrite(PIN_ALARMA_1, HIGH);
        break;
      case 2:
        digitalWrite(PIN_ALARMA_0,HIGH);
        digitalWrite(PIN_ALARMA_1, LOW);
        break;
      case 3:
        digitalWrite(PIN_ALARMA_0,HIGH);
        digitalWrite(PIN_ALARMA_1, HIGH);
        break;
      default:
        digitalWrite(PIN_ALARMA_0, LOW);
        digitalWrite(PIN_ALARMA_1, LOW);
        break;
   } 
}

void tx_send () {
  serialBT.println("SEND");
  serialBT.print("ID ");
  serialBT.println(ID);
  serialBT.print("AMOUNT ");
  serialBT.println(NUM_MUESTRAS);
  serialBT.print("MAX_VALUE ");
  serialBT.println(MAX_VALUE);
  serialBT.print("MIN_VALUE ");
  serialBT.println(MIN_VALUE);
  serialBT.println("DATA ");
  for (int j = 0; j < NUM_MUESTRAS; j++ ) {
    serialBT.println(muestras[j]);
  }
  serialBT.write('\u0017');
}

void pintaNum (int num) {
  digitalWrite(PIN_DISPLAY_LATCH, LOW);
  switch (num) {
    case 0:
      shiftOut(PIN_DISPLAY_DATA, PIN_DISPLAY_CLOCK, LSBFIRST, 0b11111100);
      break;
    case 1:
      shiftOut(PIN_DISPLAY_DATA, PIN_DISPLAY_CLOCK, LSBFIRST, 0b01100000);
      break;
    case 2:
      shiftOut(PIN_DISPLAY_DATA, PIN_DISPLAY_CLOCK, LSBFIRST, 0b11011010);
      break;
    case 3:
      shiftOut(PIN_DISPLAY_DATA, PIN_DISPLAY_CLOCK, LSBFIRST, 0b11110010);
      break;
    case 4:
      shiftOut(PIN_DISPLAY_DATA, PIN_DISPLAY_CLOCK, LSBFIRST, 0b01100110);
      break;
    case 5:
      shiftOut(PIN_DISPLAY_DATA, PIN_DISPLAY_CLOCK, LSBFIRST, 0b10110110);
      break;
    case 6:
      shiftOut(PIN_DISPLAY_DATA, PIN_DISPLAY_CLOCK, LSBFIRST, 0b10111110);
      break;
    case 7:
      shiftOut(PIN_DISPLAY_DATA, PIN_DISPLAY_CLOCK, LSBFIRST, 0b11100000);
      break;
    case 8:
      shiftOut(PIN_DISPLAY_DATA, PIN_DISPLAY_CLOCK, LSBFIRST, 0b11111110);
      break;
    case 9:
      shiftOut(PIN_DISPLAY_DATA, PIN_DISPLAY_CLOCK, LSBFIRST, 0b11100110);
      break; 
    default:
      shiftOut(PIN_DISPLAY_DATA, PIN_DISPLAY_CLOCK, LSBFIRST, 0b00000001);
      break;
  }
  digitalWrite(PIN_DISPLAY_LATCH, HIGH);
}

void setup() {
  //configuracion
  serialBT.begin(SPEED_BT);
  pinMode(PIN_DISPLAY_CLOCK, OUTPUT); 
  pinMode(PIN_DISPLAY_DATA, OUTPUT); 
  pinMode(PIN_DISPLAY_LATCH, OUTPUT); 
  pinMode(PIN_ALARMA_0, OUTPUT);  
  pinMode (PIN_ALARMA_1, OUTPUT);
  
  digitalWrite(PIN_ALARMA_0, LOW);
  digitalWrite(PIN_ALARMA_1, LOW); 
  pintaNum(-1); 
  
  started = false;
  
  contador = 0;
}

void loop() {
  if (started) {
    muestras[contador++] = analogRead(ANALOG_PIN);
    if (contador >= NUM_MUESTRAS) {
      contador = 0;
      //transmitimos
      tx_send ();
    } else {
         if(serialBT.available()>0){
           //recibimos los datos
           rx_message ();
           int numero = mensajeRX[POS_NIVEL] - '0';
           pintaNum(numero);
           int alarmLevel = mensajeRX[POS_ALARM] - '0';
           alarma (alarmLevel);
         }
      delay(ESPERA_MS);
    }
  } else {
    if(serialBT.available()>0){
      //recibimos el mensaje
      rx_start();      
      if(startRX[POS_START] == 'Y') {
        started = true;
      }
    }
  }
}
