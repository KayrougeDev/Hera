package fr.kayrouge.hera.util.type;

public enum QuestionsType {

    NOTHING(0),
    LIST(1),
    ANSWER(2)
    ;


    private final int id;

    QuestionsType(int id) {
        if (id < 0 || id > 255) throw new IllegalArgumentException("ID must be between 0 and 255");
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static QuestionsType getById(int id) {
        for (QuestionsType type : values()) {
            if(type.getId() == id) {
                return type;
            }
        }
        return QuestionsType.NOTHING;
    }
}
