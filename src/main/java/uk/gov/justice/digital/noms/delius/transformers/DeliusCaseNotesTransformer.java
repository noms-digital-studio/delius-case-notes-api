package uk.gov.justice.digital.noms.delius.transformers;

import uk.gov.justice.digital.noms.delius.data.api.CaseNote;
import uk.gov.justice.digital.noms.delius.data.api.CaseNoteBody;
import uk.gov.justice.digital.noms.delius.data.delius.DeliusCaseNote;

public class DeliusCaseNotesTransformer {

    public DeliusCaseNote toDeliusCaseNote(CaseNote caseNote) {

        CaseNoteBody body = caseNote.getBody();
        return DeliusCaseNote.builder()
                .content(body.getContent())
                .establishmentCode(body.getEstablishmentCode())
                .nomisId(caseNote.getNomisId())
                .noteId(caseNote.getNoteId())
                .staffName(body.getStaffName())
                .noteType(body.getNoteType())
                .timestamp(body.getTimestamp())
                .build();
    }
}
