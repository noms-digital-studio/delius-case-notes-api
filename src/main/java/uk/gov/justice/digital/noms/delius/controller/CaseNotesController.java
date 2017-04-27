package uk.gov.justice.digital.noms.delius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.digital.noms.delius.data.api.CaseNote;
import uk.gov.justice.digital.noms.delius.data.api.CaseNoteBody;
import uk.gov.justice.digital.noms.delius.data.delius.DeliusCaseNote;
import uk.gov.justice.digital.noms.delius.service.Service;

import java.util.Optional;

@RestController
@RequestMapping("/delius")
public class CaseNotesController {

    private final Service caseNotesService;

    @Autowired
    public CaseNotesController(Service caseNotesService) {
        this.caseNotesService = caseNotesService;
    }

    @RequestMapping("/casenote/{nomisId}/{noteId}")
    @ResponseStatus(HttpStatus.CREATED)
    public DeliusCaseNote putCaseNote(final @PathVariable("nomisId") String nomisId,
                                      final @PathVariable("noteId") String noteId,
                                      final @RequestBody CaseNoteBody caseNoteBody) {

        final CaseNote caseNote = CaseNote.builder()
                .noteId(noteId)
                .nomisId(nomisId)
                .body(caseNoteBody)
                .build();

        Optional<DeliusCaseNote> created = caseNotesService.addCaseNote(caseNote);

        return created.orElse(null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String badRequest(Exception e) {
        return e.getCause().getMessage();
    }
}
