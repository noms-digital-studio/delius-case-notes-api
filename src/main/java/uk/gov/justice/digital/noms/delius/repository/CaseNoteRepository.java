package uk.gov.justice.digital.noms.delius.repository;

import uk.gov.justice.digital.noms.delius.data.delius.DeliusCaseNote;

public class CaseNoteRepository implements Repository {

    @Override
    public boolean createCaseNote(DeliusCaseNote caseNote) {
        //TODO: Insert some stuff into Delius somehow
        return true;
    }

}
