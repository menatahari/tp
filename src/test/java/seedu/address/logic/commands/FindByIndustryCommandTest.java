package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.IndustryContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindByIndustryCommand}.
 */
public class FindByIndustryCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        // Tests for find by names
        IndustryContainsKeywordsPredicate firstIndustryPredicate =
                new IndustryContainsKeywordsPredicate(Collections.singletonList("first"));
        IndustryContainsKeywordsPredicate secondIndustryPredicate =
                new IndustryContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstIndustryCommand = new FindCommand(firstIndustryPredicate);
        FindCommand findSecondIndustryCommand = new FindCommand(secondIndustryPredicate);

        // same object -> returns true
        assertTrue(findFirstIndustryCommand.equals(findFirstIndustryCommand));

        // same values -> returns true
        FindCommand findFirstIndustryCommandCopy = new FindCommand(firstIndustryPredicate);
        assertTrue(findFirstIndustryCommand.equals(findFirstIndustryCommandCopy));

        // different types -> returns false
        assertFalse(findFirstIndustryCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstIndustryCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstIndustryCommand.equals(findSecondIndustryCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        IndustryContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        IndustryContainsKeywordsPredicate predicate = preparePredicate("web3");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        IndustryContainsKeywordsPredicate predicate = new IndustryContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code IndustryContainsKeywordsPredicate}.
     */
    private IndustryContainsKeywordsPredicate preparePredicate(String userInput) {
        return new IndustryContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
