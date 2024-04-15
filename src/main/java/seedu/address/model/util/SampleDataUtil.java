package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Description;
import seedu.address.model.person.Person;
import seedu.address.model.startup.Address;
import seedu.address.model.startup.Email;
import seedu.address.model.startup.FundingStage;
import seedu.address.model.startup.Industry;
import seedu.address.model.startup.Name;
import seedu.address.model.startup.Note;
import seedu.address.model.startup.Phone;
import seedu.address.model.startup.Startup;
import seedu.address.model.startup.Valuation;
import seedu.address.model.tag.Tag;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Startup[] getSampleStartups() {
        return new Startup[] {
            new Startup(new Name("Allium"), new FundingStage("SEED"), new Industry("WEB3"),
                new Phone("98765432"), new Email("allium@gmail.com"),
                new Address("420, 23rd Street, #02-25"), new Valuation("100000"),
            getTagSet("competitive", "owesMoney"), getNoteList(
        "Allium is a promising company in the web3 industry.",
                "The startup is developing a decentralised social media platform "
                + "that aims to revolutionise content creation and distribution.")),

            new Startup(new Name("Beta"), new FundingStage("B"), new Industry("Tech"),
                new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Valuation("30000000"),
                getTagSet("competitive"), getNoteList("Looking for Series A funding")),

            new Startup(new Name("Sigium"), new FundingStage("S"), new Industry("web3"),
                new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Valuation("100000"),
                getTagSet("nice"), getNoteList("Looking for Series C funding"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Startup sampleStartup : getSampleStartups()) {
            sampleAb.addStartup(sampleStartup);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a description set containing the list of strings given.
     */
    public static Set<Description> getDescriptionSet(String... strings) {
        return Arrays.stream(strings)
                .map(Description::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static List<Person> getPersonList(Person... persons) {
        return Arrays.stream(persons)
                .collect(Collectors.toList());
    }

    /**
     * Returns a note list containing the notes given as strings.
     */
    public static List<Note> getNoteList(String... strings) {
        return Arrays.stream(strings)
                .map(Note::new)
                .collect(Collectors.toList());
    }
}

