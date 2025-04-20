package fr.kayrouge.hera.util.type.game;

public enum TerritoryType {

    NOTHING(0),
    SET(1),
    VISIBLE(2)
    ;


    private final int id;

    TerritoryType(int id) {
        if (id < 0 || id > 255) throw new IllegalArgumentException("ID must be between 0 and 255");
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TerritoryType getById(int id) {
        for (TerritoryType type : values()) {
            if(type.getId() == id) {
                return type;
            }
        }
        return TerritoryType.NOTHING;
    }

}
