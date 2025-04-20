package fr.kayrouge.hera.util.type;

public enum PacketType {

    NOTHING(0),
    JOIN(1),
    QUESTION(2),
    TERRITORY_GAME(3),
    ;


    private final int id;

    PacketType(int id) {
        if (id < 0 || id > 255) throw new IllegalArgumentException("ID must be between 0 and 255");
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static PacketType getById(int id) {
        for (PacketType type : values()) {
            if(type.getId() == id) {
                return type;
            }
        }
        return PacketType.NOTHING;
    }
}
