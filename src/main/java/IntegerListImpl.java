import exceptions.ElementNotFoundException;
import exceptions.InputNullException;
import exceptions.StringListOutOfBoundsException;

import java.util.Arrays;
import java.util.Objects;

public class IntegerListImpl implements IntegerList{

    private Integer[] integerListStorage;

    private int size = 0;
    public IntegerListImpl(int size) {
        integerListStorage = new Integer[size];
    }
    @Override
    public Integer add(Integer item) {
        inputValidate(item);
        if (size == integerListStorage.length) {
            integerListStorage = grow(integerListStorage);
        }
        integerListStorage[size++] = item;
        return integerListStorage[size - 1];
    }

    @Override
    public Integer add(int index, Integer item) {
        inputValidate(item);
        if (size == integerListStorage.length) {
            integerListStorage = grow(integerListStorage);
        }
        if (index > size -1) {
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
        Integer[] arr = toArray();
        mergeSort(arr);
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

    private void mergeSort(Integer[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[arr.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    private static void merge(Integer[] arr, Integer[] left, Integer[] right) {

        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }

    private Integer[] grow(Integer[] arr) {
        return Arrays.copyOf(arr, (int) (arr.length * 1.5));
    }
}
