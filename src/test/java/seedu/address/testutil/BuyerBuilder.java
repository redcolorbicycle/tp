package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.house.HousingType;
import seedu.address.model.person.Budget;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;


/**
 * A Utility class to help with building Buyer objects.
 */
public class BuyerBuilder {
    public static final String DEFAULT_NAME = "James Lim";
    public static final String DEFAULT_PHONE = "93840172";
    public static final String DEFAULT_EMAIL = "james@gmail.com";
    public static final String DEFAULT_BUDGET = "666000";
    public static final String DEFAULT_HOUSING_TYPE = "Hdb";

    private Name name;
    private Phone phone;
    private Email email;
    private HousingType housingType;
    private Budget budget;
    private Set<Tag> tags;

    /**
     * Creates a {@code BuyerBuilder} with the default information.
     */
    public BuyerBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        housingType = new HousingType(DEFAULT_HOUSING_TYPE);
        budget = new Budget(DEFAULT_BUDGET);
        tags = new HashSet<>();
    }

    /**
     * Initializes the BuyerBuilder with the data of {@code personToCopy}
     */
    public BuyerBuilder(Buyer personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        housingType = personToCopy.getHousingType();
        budget = personToCopy.getBudget();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Buyer} that we are building.
     */
    public BuyerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Buyer} that we are building.
     */
    public BuyerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Buyer} that we are building.
     */
    public BuyerBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Buyer} that we are building.
     */
    public BuyerBuilder withHousingType(String housingType) {
        this.housingType = new HousingType(housingType);
        return this;
    }

    /**
     * Sets the {@code Budget} of the {@code Buyer} that we are building.
     */
    public BuyerBuilder withBudget(String budget) {
        this.budget = new Budget(budget.trim());
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public BuyerBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Buyer build() {
        return new Buyer(name, phone, email, housingType, budget, tags);
    }
}
