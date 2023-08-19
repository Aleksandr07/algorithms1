import exceptions.ElementNotFoundException;
import exceptions.InputNullException;
import exceptions.StringListOutOfBoundsException;

import java.util.Arrays;

public class StringListImpl implements StringList{

    private final String[] stringListStorage;

    private int count = 0;
    public StringListImpl(int size) {
        stringListStorage = new String[size];
    }

    @Override
    public String add(String item) {
        inputValidate(item);
        if (count == stringListStorage.length) {
            throw new StringListOutOfBoundsException("Превышен размер массива");
        }
        stringListStorage[count] = item;
        count++;
        return stringListStorage[count - 1];
    }

    @Override
    public String add(int index, String item) {
        inputValidate(item);
        if (index == stringListStorage.length || index > count-1) {
            throw new StringListOutOfBoundsException("Превышен размер массива");
        }
        for (int i = count-1; i >= index; i--) {
                stringListStorage[i+1] = stringListStorage[i];
        }
        count++;
        stringListStorage[index] = item;
        return stringListStorage[index];
    }

    @Override
    public String set(int index, String item) {
        inputValidate(item);
        if (index == stringListStorage.length || index > count-1) {
            throw new StringListOutOfBoundsException("Превышен размер массива");
        }
        stringListStorage[index] = item;
        return stringListStorage[index];
    }

    @Override
    public String remove(String item) {
        int index = -1;
        String deletedElement = null;
        for (int i = 0; i < count-1; i++) {
            if (stringListStorage[i].equals(item)) {
                index = i;
                deletedElement = stringListStorage[i];
                break;
            }
        }
        if (index == -1) {
            throw new ElementNotFoundException("Элемент массива не найден");
        }
        for (int i = index; i < count-1; i++) {
            stringListStorage[i] = stringListStorage[i + 1];
        }
        count--;
        return deletedElement;
    }

    @Override
    public String remove(int index) {
        if (index > count-1) {
            throw new ElementNotFoundException("Элемент массива не найден");
        }
        String deletedElement = stringListStorage[index];
        for (int i = index; i < count-1; i++) {
            stringListStorage[i] = stringListStorage[i + 1];
        }
        count--;
        return deletedElement;
    }

    @Override
    public boolean contains(String item) {
        boolean isContains = false;
        for (int i = 0; i < count-1; i++) {
            if (stringListStorage[i].equals(item)) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }

    @Override
    public int indexOf(String item) {
        int findElementIndex = -1;
        for (int i = 0; i < count-1; i++) {
            if (stringListStorage[i].equals(item)) {
                findElementIndex = i;
                break;
            }
        }
        return findElementIndex;
    }

    @Override
    public int lastIndexOf(String item) {
        int index = -1;
        for (int i = count-1; i >= 0; i--) {
            if (stringListStorage[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public String get(int index) {
        if (index > stringListStorage.length || index > count-1) {
            throw new StringListOutOfBoundsException("Превышен размер массива");
        }
        return stringListStorage[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        boolean isEquals = true;
        if (size() != otherList.size()) {
            isEquals = false;
        } else {
            for (int i = 0; i < count-1; i++) {
                if (!(stringListStorage[i].equals(otherList.get(i)))) {
                    isEquals = false;
                }
            }
        }
        return isEquals;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i <= count-1; i++) {
            stringListStorage[i] = null;
        }
        count = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(stringListStorage, count);
    }

    private void inputValidate(String item) {
        if (item == null) {
            throw new InputNullException("null хранить нельзя");
        }
    }
}
