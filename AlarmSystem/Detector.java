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
