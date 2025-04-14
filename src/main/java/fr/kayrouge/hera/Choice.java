package fr.kayrouge.hera;

import com.google.common.io.ByteArrayDataOutput;

import java.io.DataInputStream;
import java.io.IOException;

public class Choice {

    private final String name;
    private final Type type;

    public Choice(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public static Choice of(String name, Type type) {
        return new Choice(name, type);
    }
    public static Choice of(String name) {
        return new Choice(name, Type.SIMPLE_BUTTON);
    }

    public void toPacket(ByteArrayDataOutput output) {
        output.writeUTF(this.name);
        output.writeInt(this.type.getId());
    }

    public static Choice fromPacket(DataInputStream input) throws IOException {
        return of(input.readUTF(), Type.getById(input.readInt()));
    }


    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        SIMPLE_BUTTON(0),
        TEXT_ENTRY(1),
        INT_ENTRY(2),
        ;

        private final int id;

        Type(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static Type getById(int id) {
            for(Type type: values()) {
                if(type.getId() == id) {
                    return type;
                }
            }
            return Type.SIMPLE_BUTTON;
        }
    }
}
