import exceptions.ElementNotFoundException;
import exceptions.InputNullException;
import exceptions.StringListOutOfBoundsException;

import java.util.Arrays;
import java.util.Objects;

public class IntegerListImpl implements IntegerList{

    private final Integer[] integerListStorage;

    private int size = 0;
    public IntegerListImpl(int size) {
        integerListStorage = new Integer[size];
    }
    @Override
    public Integer add(Integer item) {
        inputValidate(item);
        if (size == integerListStorage.length) {
            throw new StringListOutOfBoundsException("Превышен размер массива");
        }
        integerListStorage[size++] = item;
        return integerListStorage[size - 1];
    }

    @Override
    public Integer add(int index, Integer item) {
        inputValidate(item);
        if (index == integerListStorage.length || index > size -1) {
            throw new StringListOutOfBoundsException("Превышен размер массива");
        }
        System.arraycopy(integerListStorage, index, integerListStorage, index + 1, size - index );
        integerListStorage[index] = item;
        size++;
        return integerListStorage[index];
    }

    @Override
    public Integer set(int index, Integer item) {
        inputValidate(item);
        if (index == integerListStorage.length || index > size -1) {
            throw new StringListOutOfBoundsException("Превышен размер массива");
        }
        integerListStorage[index] = item;
        return integerListStorage[index];
    }

    @Override
    public Integer removeSearchItem(Integer item) {
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException("Элемент массива не найден");
        }

        if (index != size) {
            System.arraycopy(integerListStorage, index + 1, integerListStorage, index, size - index);
        }
        size--;

        return item;
    }

    @Override
    public Integer remove(int index) {
        if (index > size -1) {
            throw new ElementNotFoundException("Элемент массива не найден");
        }
        Integer item = integerListStorage[index];
        if (index != size) {
            System.arraycopy(integerListStorage, index + 1, integerListStorage, index, size - index);
        }
        size--;

        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] arr = Arrays.copyOf(integerListStorage, size);
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (Objects.equals(item, arr[mid])) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (integerListStorage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (integerListStorage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index > integerListStorage.length || index > size -1) {
            throw new StringListOutOfBoundsException("Превышен размер массива");
        }
        return integerListStorage[index];    }

    @Override
    public boolean equals(IntegerList otherList) {
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
    public Integer[] toArray() {
        return Arrays.copyOf(integerListStorage, size);
    }

    private void inputValidate(Integer item) {
        if (item == null) {
            throw new InputNullException("null хранить нельзя");
        }
    }

    private void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
