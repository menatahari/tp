package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStartup.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStartups.STARTUP2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.startup.Address;
import seedu.address.model.startup.Email;
import seedu.address.model.startup.FundingStage;
import seedu.address.model.startup.Industry;
import seedu.address.model.startup.Name;
import seedu.address.model.startup.Note;
import seedu.address.model.startup.Phone;
import seedu.address.model.startup.Valuation;

public class JsonAdaptedStartupTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String INVALID_VALUATION = "-1";

    private static final String INVALID_FUNDING = "D";

    private static final String INVALID_INDUSTRY = " ";

    private static final String VALID_NAME = STARTUP2.getName().toString();
    private static final String VALID_PHONE = STARTUP2.getPhone().toString();
    private static final String VALID_EMAIL = STARTUP2.getEmail().toString();
    private static final String VALID_ADDRESS = STARTUP2.getAddress().toString();

    private static final String VALID_VALUATION = STARTUP2.getValuation().toString();

    private static final String VALID_FUNDING = STARTUP2.getFundingStage().toString();

    private static final String VALID_INDUSTRY = STARTUP2.getIndustry().toString();

    private static final List<String> VALID_NOTES = STARTUP2.getNotes().stream()
            .map(Note::toString)
            .collect(Collectors.toList());

    private static final List<JsonAdaptedTag> VALID_TAGS = STARTUP2.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    private static final List<JsonAdaptedPerson> VALID_PERSONS = STARTUP2.getPersons().stream()
            .map(JsonAdaptedPerson::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validStartupDetails_returnsStartup() throws Exception {
        JsonAdaptedStartup startup = new JsonAdaptedStartup(STARTUP2);
        assertEquals(STARTUP2, startup.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStartup startup =
                new JsonAdaptedStartup(INVALID_NAME, VALID_INDUSTRY, VALID_FUNDING,
                    VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VALUATION,
                        VALID_TAGS, VALID_NOTES, VALID_PERSONS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, startup::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStartup startup = new JsonAdaptedStartup(null, VALID_INDUSTRY, VALID_FUNDING,
            VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VALUATION, VALID_TAGS, VALID_NOTES,
                VALID_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, startup::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStartup startup =
                new JsonAdaptedStartup(VALID_NAME, VALID_INDUSTRY, VALID_FUNDING,
                    INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VALUATION, VALID_TAGS, VALID_NOTES,
                        VALID_PERSONS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, startup::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedStartup startup = new JsonAdaptedStartup(VALID_NAME, VALID_INDUSTRY, VALID_FUNDING,
            null, VALID_EMAIL, VALID_ADDRESS, VALID_VALUATION, VALID_TAGS, VALID_NOTES, VALID_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, startup::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStartup startup =
                new JsonAdaptedStartup(VALID_NAME, VALID_INDUSTRY, VALID_FUNDING,
                    VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_VALUATION, VALID_TAGS,
                        VALID_NOTES, VALID_PERSONS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, startup::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStartup startup = new JsonAdaptedStartup(VALID_NAME, VALID_INDUSTRY, VALID_FUNDING,
            VALID_PHONE, null, VALID_ADDRESS, VALID_VALUATION, VALID_TAGS, VALID_NOTES, VALID_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, startup::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedStartup startup =
                new JsonAdaptedStartup(VALID_NAME, VALID_INDUSTRY, VALID_FUNDING,
                    VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_VALUATION, VALID_TAGS, VALID_NOTES,
                        VALID_PERSONS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, startup::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedStartup startup = new JsonAdaptedStartup(VALID_NAME, VALID_INDUSTRY, VALID_FUNDING,
            VALID_PHONE, VALID_EMAIL, null, VALID_VALUATION, VALID_TAGS, VALID_NOTES, VALID_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, startup::toModelType);
    }

    @Test
    public void toModelType_invalidIndustry_throwsIllegalValueException() {
        JsonAdaptedStartup startup =
            new JsonAdaptedStartup(VALID_NAME, INVALID_INDUSTRY, VALID_FUNDING,
            VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VALUATION, VALID_TAGS, VALID_NOTES, VALID_PERSONS);
        String expectedMessage = Industry.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, startup::toModelType);
    }

    @Test
    public void toModelType_nullIndustry_throwsIllegalValueException() {
        JsonAdaptedStartup startup = new JsonAdaptedStartup(VALID_NAME, null, VALID_FUNDING,
            VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VALUATION, VALID_TAGS, VALID_NOTES,
                VALID_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Industry.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, startup::toModelType);
    }

    @Test
    public void toModelType_invalidFundingStage_throwsIllegalValueException() {
        JsonAdaptedStartup startup =
            new JsonAdaptedStartup(VALID_NAME, VALID_INDUSTRY, INVALID_FUNDING,
            VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VALUATION, VALID_TAGS, VALID_NOTES,
                    VALID_PERSONS);
        String expectedMessage = FundingStage.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, startup::toModelType);
    }

    @Test
    public void toModelType_nullFundingStage_throwsIllegalValueException() {
        JsonAdaptedStartup startup = new JsonAdaptedStartup(VALID_NAME, VALID_INDUSTRY, null,
            VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VALUATION, VALID_TAGS, VALID_NOTES,
                VALID_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, FundingStage.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, startup::toModelType);
    }

    @Test
    public void toModelType_invalidValuation_throwsIllegalValueException() {
        JsonAdaptedStartup startup = new JsonAdaptedStartup(VALID_NAME, VALID_INDUSTRY, VALID_FUNDING,
            VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, INVALID_VALUATION, VALID_TAGS, VALID_NOTES,
                VALID_PERSONS);
        String expectedMessage = Valuation.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, startup::toModelType);
    }

    @Test
    public void toModelType_nullValuation_throwsIllegalValueException() {
        JsonAdaptedStartup startup = new JsonAdaptedStartup(VALID_NAME, VALID_INDUSTRY, VALID_FUNDING,
            VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, null, VALID_TAGS, VALID_NOTES,
                VALID_PERSONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Valuation.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, startup::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStartup startup =
                new JsonAdaptedStartup(VALID_NAME, VALID_INDUSTRY, VALID_FUNDING,
                    VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VALUATION, invalidTags, VALID_NOTES, VALID_PERSONS);
        assertThrows(IllegalValueException.class, startup::toModelType);
    }

}
