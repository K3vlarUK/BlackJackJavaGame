public enum SuitType {
    SPADES,
    HEARTS,
    CLUBS,
    DIAMONDS;

    public String toString() {
        String firstLetter =  this.name().substring(0, 1);
        String restOfWord = this.name().substring(1).toLowerCase();
        return firstLetter + restOfWord;
    }
}
