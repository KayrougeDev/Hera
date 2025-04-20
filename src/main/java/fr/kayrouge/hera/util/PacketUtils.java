package fr.kayrouge.hera.util;

import org.jspecify.annotations.Nullable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

public class PacketUtils {
    public static final byte TYPE_NULL = 0;
    public static final byte TYPE_STRING = 1;
    public static final byte TYPE_INT = 2;
    public static final byte TYPE_BOOLEAN = 3;
    public static final byte TYPE_DOUBLE = 4;
    public static final byte TYPE_FLOAT = 5;
    public static final byte TYPE_LONG = 6;
    public static final byte TYPE_BYTE = 7;

    public static void writeObject(DataOutputStream out, Object obj) throws IOException {
        if(Objects.isNull(obj)) {
            out.writeByte(TYPE_NULL);
        }
        else if (obj instanceof String s) {
            out.writeByte(TYPE_STRING);
            out.writeUTF(s);
        } else if (obj instanceof Integer i) {
            out.writeByte(TYPE_INT);
            out.writeInt(i);
        } else if (obj instanceof Boolean b) {
            out.writeByte(TYPE_BOOLEAN);
            out.writeBoolean(b);
        } else if (obj instanceof Double d) {
            out.writeByte(TYPE_DOUBLE);
            out.writeDouble(d);
        } else if (obj instanceof Float f) {
            out.writeByte(TYPE_FLOAT);
            out.writeFloat(f);
        } else if (obj instanceof Long l) {
            out.writeByte(TYPE_LONG);
            out.writeLong(l);
        } else if (obj instanceof Byte b) {
            out.writeByte(TYPE_BYTE);
            out.writeByte(b);
        } else {
            throw new IllegalArgumentException("Unsupported object type: " + obj.getClass());
        }
    }

    @Nullable
    public static Object readObject(DataInputStream in) throws IOException {
        byte type = in.readByte();
        return switch (type) {
            case PacketUtils.TYPE_NULL -> null;
            case PacketUtils.TYPE_STRING -> in.readUTF();
            case PacketUtils.TYPE_INT -> in.readInt();
            case PacketUtils.TYPE_BOOLEAN -> in.readBoolean();
            case PacketUtils.TYPE_DOUBLE -> in.readDouble();
            case PacketUtils.TYPE_FLOAT -> in.readFloat();
            case PacketUtils.TYPE_LONG -> in.readLong();
            case PacketUtils.TYPE_BYTE -> in.readByte();
            default -> throw new IOException("Unknown data type ID: " + type);
        };
    }
}
