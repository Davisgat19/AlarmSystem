class DoorDetector extends Detector {
    public void triggerAlarm() {
        if (isActive) {
            System.out.println("Dörr öppnad! Larm utlöst!");
        }
    }
}
