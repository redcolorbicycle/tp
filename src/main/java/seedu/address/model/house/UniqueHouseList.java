package seedu.address.model.house;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.house.exceptions.DuplicateHouseException;
import seedu.address.model.house.exceptions.HouseNotFoundException;


/**
 * A list of houses that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code House#isSamePerson(House)}. As such, adding and updating of
 * persons uses House#isSamePerson(House) for equality so as to ensure that the house being added or updated is
 * unique in terms of identity in the UniqueHouseList. However, the removal of a house uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 */
public class UniqueHouseList implements Iterable<House> {

    private final ObservableList<House> internalList = FXCollections.observableArrayList();
    private final ObservableList<House> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(House toCheck) {
        requireNonNull(toCheck);
        for (House h: internalList) {
            int addressOfHouseIndex = toCheck.toString().toLowerCase().indexOf("street");
            int addressOfHouseInListIndex = h.toString().toLowerCase().indexOf("street");
            int cutOffPriceOfHouseIndex = toCheck.toString().toLowerCase().lastIndexOf("price");
            int cutOffPriceOfHouseInListIndex = h.toString().toLowerCase().lastIndexOf("price");

            String firstAddress = toCheck.toString().substring(addressOfHouseIndex + "street".length(),
                                                               cutOffPriceOfHouseIndex).toLowerCase();
            String secondAddress = h.toString().substring(addressOfHouseInListIndex + "street".length(),
                                                          cutOffPriceOfHouseInListIndex).toLowerCase();
            if (firstAddress.equals(secondAddress)) {
                return true;
            }
        }
        return false;

    }

    /**
     * Adds a house to the list.
     * The house must not already exist in the list.
     */
    public void add(House toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            Logger.getLogger(UniqueHouseList.class.getName()).log(Level.WARNING,
                    "Duplicate house detected: " + toAdd.toString());
            return;
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent house from the list.
     * The house must exist in the list.
     */
    public void remove(House toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new HouseNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<House> asUnmodifiableObservableListHouse() {
        return internalUnmodifiableList;
    }

    /**
     * Replaces the contents of this list with {@code houses}.
     * {@code houses} must not contain duplicate houses.
     */
    public void setHouses(List<House> houses) {
        requireAllNonNull(houses);
        if (!housesAreUnique(houses)) {
            throw new DuplicateHouseException();
        }

        internalList.setAll(houses);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<House> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<House> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueHouseList)) {
            return false;
        }

        UniqueHouseList otherUniqueHouseList = (UniqueHouseList) other;
        return internalList.equals(otherUniqueHouseList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code houses} contains only unique houses.
     */
    private boolean housesAreUnique(List<House> houses) {
        for (int i = 0; i < houses.size() - 1; i++) {
            for (int j = i + 1; j < houses.size(); j++) {
                if (houses.get(i).equals(houses.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
