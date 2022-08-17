public enum Digit {
    I("1"), II("2"), III("3"), IV("4"), V("5"), VI("6"), VII("7"),
    VIII("8"), IX("9"), X("10"), L("50"), C("100");

    private String arabic;

    Digit(String arabic) {
        this.arabic = arabic;
    }
    public String getArabic() {
        return arabic;
    }
}
