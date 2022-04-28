package uk.co.renbinden.libdnd.ability;

public enum Ability {

    STRENGTH("Strength", "Measuring physical power", "STR"),
    DEXTERITY("Dexterity", "Measuring agility", "DEX"),
    CONSTITUTION("Constitution", "Measuring endurance", "CON"),
    INTELLIGENCE("Intelligence", "Measuring reasoning and memory", "INT"),
    WISDOM("Wisdom", "Measuring perception and insight", "WIS"),
    CHARISMA("Charisma", "Measuring force of personality", "CHA");

    private final String name;
    private final String description;
    private final String abbreviation;

    Ability(String name, String description, String abbreviation) {
        this.name = name;
        this.description = description;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
