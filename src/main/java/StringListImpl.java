import exceptions.ElementNotFoundException;
import exceptions.InputNullException;
import exceptions.StringListOutOfBoundsException;

import java.util.Arrays;

public class StringListImpl implements StringList{

    private final String[] stringListStorage;

    private int size = 0;
    public StringListImpl(int size) {
        stringListStorage = new String[size];
    }

    @Override
    public String add(String item) {
        inputValidate(item);
        if (size == stringListStorage.length) {
            throw new StringListOutOfBoundsException("Превышен размер массива");
        }
        stringListStorage[size++] = item;
        return stringListStorage[size - 1];
    }

    @Override
    public String add(int index, String item) {
        inputValidate(item);
        if (index == stringListStorage.length || index > size -1) {
            throw new StringListOutOfBoundsException("Превышен размер массива");
        }
        System.arraycopy(stringListStorage, index, stringListStorage, index + 1, size - index );
         stringListStorage[index] = item;
        size++;
        return stringListStorage[index];
    }

    @Override
    public String set(int index, String item) {
        inputValidate(item);
        if (index == stringListStorage.length || index > size -1) {
            throw new StringListOutOfBoundsException("Превышен размер массива");
        }
        stringListStorage[index] = item;
        return stringListStorage[index];
    }

    @Override
    public String remove(String item) {
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException("Элемент массива не найден");
        }

        if (index != size) {
            System.arraycopy(stringListStorage, index + 1, stringListStorage, index, size - index);
        }
        size--;

        return item;
    }

    @Override
    public String remove(int index) {
        if (index > size -1) {
            throw new ElementNotFoundException("Элемент массива не найден");
        }
        String item = stringListStorage[index];
        if (index != size) {
            System.arraycopy(stringListStorage, index + 1, stringListStorage, index, size - index);
        }
        size--;

        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (stringListStorage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (stringListStorage[i].equals(item)) {
                return i;
                            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index > stringListStorage.length || index > size -1) {
            throw new StringListOutOfBoundsException("Превышен размер массива");
        }
        return stringListStorage[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(stringListStorage, size);
    }

    private void inputValidate(String item) {
        if (item == null) {
            throw new InputNullException("null хранить нельзя");
        }
    }
}
