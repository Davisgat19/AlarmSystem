class WindowDetector extends Detector {
    public void triggerAlarm() {
        if (isActive) {
            System.out.println("Fönster öppnat eller krossat! Larm utlöst!");
        }
    }
}
