package seedu.address.model.house;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UnitNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnitNumber(null));
    }

    @Test
    public void constructor_invalidUnitNumber_throwsIllegalArgumentException() {
        String invalidUnitNumber = "abcd";
        assertThrows(IllegalArgumentException.class, () -> new UnitNumber(invalidUnitNumber));
    }

    @Test
    public void isValidUnitNumber() {
        // null unit number
        assertThrows(NullPointerException.class, () -> UnitNumber.isValidUnitNumber(null));

        // invalid unit numbers
        assertFalse(UnitNumber.isValidUnitNumber("0")); // '0' alone is invalid
        assertFalse(UnitNumber.isValidUnitNumber("00")); // multiple zeros invalid
        assertFalse(UnitNumber.isValidUnitNumber("0000")); // four digits are invalid
        assertFalse(UnitNumber.isValidUnitNumber("0A")); // 1 zero + alphabet
        assertFalse(UnitNumber.isValidUnitNumber("00A")); // 2 zeroes + alphabet
        assertFalse(UnitNumber.isValidUnitNumber("000A")); // 3 zeroes + alphabet
        assertFalse(UnitNumber.isValidUnitNumber("")); // empty string
        assertFalse(UnitNumber.isValidUnitNumber(" ")); // spaces only
        assertFalse(UnitNumber.isValidUnitNumber("A")); // character only
        assertFalse(UnitNumber.isValidUnitNumber("1000")); // four digits without a letter are invalid
        assertFalse(UnitNumber.isValidUnitNumber("14 1")); // spaces within digits
        assertFalse(UnitNumber.isValidUnitNumber("1a2b")); // invalid mixed letters and numbers

        // valid unit numbers
        assertTrue(UnitNumber.isValidUnitNumber("1")); // single digit
        assertTrue(UnitNumber.isValidUnitNumber("10")); // two digits
        assertTrue(UnitNumber.isValidUnitNumber("100")); // three digits
        assertTrue(UnitNumber.isValidUnitNumber("10A")); // two digits followed by a letter
        assertTrue(UnitNumber.isValidUnitNumber("100A")); // three digits followed by a letter
        assertTrue(UnitNumber.isValidUnitNumber("1A")); // one digit followed by a letter
    }

    @Test
    public void equals() {
        UnitNumber unitNumber = new UnitNumber("12");

        // same values -> returns true
        assertTrue(unitNumber.equals(new UnitNumber("12")));

        // same object -> returns true
        assertTrue(unitNumber.equals(unitNumber));

        // null -> returns false
        assertFalse(unitNumber.equals(null));

        // different types -> returns false
        assertFalse(unitNumber.equals(5.0f));

        // different unit number -> returns false
        assertFalse(unitNumber.equals(new UnitNumber("34")));
    }
}
