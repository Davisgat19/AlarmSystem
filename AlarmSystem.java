import java.util.Scanner;

// Gemensamt gränssnitt för alla sensorer
interface Sensor {
    void triggerAlarm();
}

// Gemensam abstrakt klass för detektorer (fönster, dörr, rök)
abstract class Detector implements Sensor {
    boolean isActive;

    Detector() {
        this.isActive = false;
    }

    void activate() {
        this.isActive = true;
    }

    void deactivate() {
        this.isActive = false;
    }
}

// Klass för fönsterdetektor
class WindowDetector extends Detector {
    @Override
    public void triggerAlarm() {
        if (isActive) {
            System.out.println("Fönster öppnat eller krossat! Larm utlöst!");
        }
    }
}

// Klass för dörrdetektor
class DoorDetector extends Detector {
    @Override
    public void triggerAlarm() {
        if (isActive) {
            System.out.println("Dörr öppnad! Larm utlöst!");
        }
    }
}

// Klass för rökdetektor
class SmokeDetector extends Detector {
    @Override
    public void triggerAlarm() {
        System.out.println("Rökdetektor utlöst! Aktiverar sprinklersystemet i det aktuella rummet.");
    }
}

// Klass för rörelsedetektor
class MotionDetector implements Sensor {
    @Override
    public void triggerAlarm() {
        System.out.println("Rörelsedetektor utlöst i poolområdet! Larm utlöst!");
    }
}

// Huvudklass för larmsystemet
public class AlarmSystem {
    private boolean isSystemArmed;
    private SmokeDetector[] smokeDetectors;
    private WindowDetector[] windowDetectors;
    private DoorDetector[] doorDetectors;
    private MotionDetector motionDetector;

    public AlarmSystem () {
        this.isSystemArmed = false;
        this.smokeDetectors = new SmokeDetector[5]; // Antag 5 rum med rökdetektorer
        this.windowDetectors = new WindowDetector[5]; // Antag 5 fönster
        this.doorDetectors = new DoorDetector[6]; // Antag 6 dörrar (inklusive garageport)
        this.motionDetector = new MotionDetector ();
        initializeDetectors ();
    }

    private void initializeDetectors () {
        for (int i = 0; i < 5; i++) {
            smokeDetectors[i] = new SmokeDetector ();
            windowDetectors[i] = new WindowDetector ();
        }
        for (int i = 0; i < 6; i++) {
            doorDetectors[i] = new DoorDetector ();
        }
    }

    public void armSystem () {
        isSystemArmed = true;
        System.out.println ("Larmsystemet är aktiverat.");
    }

    public void disarmSystem () {
        isSystemArmed = false;
        System.out.println ("Larmsystemet är avaktiverat.");
    }

    public void simulateEvent ( String event ) {
        if (!isSystemArmed) {
            System.out.println ("Larmsystemet är inte aktiverat. Aktivera det först.");
            return;
        }

        switch (event.toLowerCase ()) {
            case "brand":
                simulateFire ();
                break;
            case "inbrott":
                simulateBreakIn ();
                break;
            case "rörelse":
                simulateMotion ();
                break;
            default:
                System.out.println ("Ogiltigt evenemang.");
        }
    }

    private void simulateFire () {
        System.out.println ("Brand simuleras!");
        for (SmokeDetector smokeDetector : smokeDetectors) {
            smokeDetector.triggerAlarm ();
        }
    }

    private void simulateBreakIn () {
        System.out.println ("Inbrott simuleras!");
        for (WindowDetector windowDetector : windowDetectors) {
            windowDetector.triggerAlarm ();
        }
        for (DoorDetector doorDetector : doorDetectors) {
            doorDetector.triggerAlarm ();
        }
    }

    private void simulateMotion () {
        System.out.println ("Rörelse simuleras i poolområdet!");
        motionDetector.triggerAlarm ();
    }

    public static void main ( String[] args ) {
        getAlarmSystem ();

    }

    private static void getAlarmSystem () {
        Scanner scanner = new Scanner (System.in);
        AlarmSystem alarmSystem = new AlarmSystem ();
        {
            int choice;
            try {
                do {
                    System.out.println ("Välj ett alternativ:");
                    System.out.println ("1. Aktivera larmsystemet");
                    System.out.println ("2. Avaktivera larmsystemet");
                    System.out.println ("3. Simulera händelse");
                    System.out.println ("0. Avsluta");

                    choice = scanner.nextInt ();
                    switch (choice) {
                        case 1:
                            alarmSystem.armSystem ();
                            break;
                        case 2:
                            alarmSystem.disarmSystem ();
                            break;
                        case 3:
                            System.out.println ("Ange händelse att simulera (brand, inbrott, rörelse):");
                            String event = scanner.next ();
                            alarmSystem.simulateEvent (event);
                            break;
                        case 0:
                            System.out.println ("Avslutar larmsystemet.");
                            break;
                        default:
                            System.out.println ("Ogiltigt val. Försök igen.");
                    }

                } while (choice != 0);
            } catch (Exception e) {
                System.out.println ("Måste ange ett heltal. Försök igen!");
                getAlarmSystem();


            }
        }
    }
}
