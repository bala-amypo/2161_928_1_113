@Override
public IntegrityCase createCase(IntegrityCase integrityCase) {

    if (integrityCase.getStudentProfile() == null ||
        integrityCase.getStudentProfile().getId() == null) {
        throw new IllegalArgumentException("StudentProfile ID is required");
    }

    Long studentId = integrityCase.getStudentProfile().getId();

    StudentProfile profile = studentProfileRepository.findById(studentId)
            .orElseThrow(() -> new IllegalArgumentException("StudentProfile not found"));

    integrityCase.setStudentProfile(profile);

    if (integrityCase.getStatus() == null) {
        integrityCase.setStatus("OPEN");
    }

    return integrityCaseRepository.save(integrityCase);
}
