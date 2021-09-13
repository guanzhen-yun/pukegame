import java.util.Objects;

public class Puke implements Comparable<Puke> {
    public Puke(String type, int num, String name) {
        this.type = type;
        this.num = num;
        this.name = name;
    }

    private String type;//方片 梅花 红桃 黑桃
    private String name;
    private int num;

    public int getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(int typeIndex) {
        this.typeIndex = typeIndex;
    }

    private int typeIndex;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return type + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Puke puke = (Puke) o;
        return num == puke.num &&
                type.equals(puke.type) &&
                name.equals(puke.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, num);
    }

    @Override
    public int compareTo(Puke o) {
        if(this.typeIndex != o.typeIndex) {
            return this.typeIndex - o.typeIndex;
        }
        return this.num - o.num;
    }
}
