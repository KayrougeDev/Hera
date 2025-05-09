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
        if (obj == null) {
            out.writeByte(TYPE_NULL);
        } else if (obj instanceof String) {
            out.writeByte(TYPE_STRING);
            out.writeUTF((String) obj);
        } else if (obj instanceof Integer) {
            out.writeByte(TYPE_INT);
            out.writeInt((Integer) obj);
        } else if (obj instanceof Boolean) {
            out.writeByte(TYPE_BOOLEAN);
            out.writeBoolean((Boolean) obj);
        } else if (obj instanceof Double) {
            out.writeByte(TYPE_DOUBLE);
            out.writeDouble((Double) obj);
        } else if (obj instanceof Float) {
            out.writeByte(TYPE_FLOAT);
            out.writeFloat((Float) obj);
        } else if (obj instanceof Long) {
            out.writeByte(TYPE_LONG);
            out.writeLong((Long) obj);
        } else if (obj instanceof Byte) {
            out.writeByte(TYPE_BYTE);
            out.writeByte((Byte) obj);
        } else {
            throw new IllegalArgumentException("Unsupported object type: " + obj.getClass());
        }
    }

    public static Object readObject(DataInputStream in) throws IOException {
        byte type = in.readByte();
        switch (type) {
            case TYPE_NULL:
                return null;
            case TYPE_STRING:
                return in.readUTF();
            case TYPE_INT:
                return in.readInt();
            case TYPE_BOOLEAN:
                return in.readBoolean();
            case TYPE_DOUBLE:
                return in.readDouble();
            case TYPE_FLOAT:
                return in.readFloat();
            case TYPE_LONG:
                return in.readLong();
            case TYPE_BYTE:
                return in.readByte();
            default:
                throw new IOException("Unknown data type ID: " + type);
        }
    }
}
