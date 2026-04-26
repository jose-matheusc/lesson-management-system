-- STUDENT table
CREATE TABLE IF NOT EXISTS student (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    base_hourly_rate DOUBLE PRECISION NOT NULL CHECK (base_hourly_rate >= 0),
    school_year VARCHAR(50) NOT NULL,
    goal VARCHAR(255) NOT NULL
);

-- GUARDIAN table
CREATE TABLE IF NOT EXISTS guardian (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    phones TEXT,
    emails TEXT,
    address VARCHAR(500)
);

-- STUDENT_GUARDIAN mapping table (many-to-many with relationship type)
CREATE TABLE IF NOT EXISTS student_guardian (
    id BIGSERIAL PRIMARY KEY,
    student_id BIGINT NOT NULL,
    guardian_id BIGINT NOT NULL,
    relationship_type VARCHAR(100) NOT NULL,
    CONSTRAINT fk_student_guardian_student FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE,
    CONSTRAINT fk_student_guardian_guardian FOREIGN KEY (guardian_id) REFERENCES guardian (id) ON DELETE CASCADE,
    UNIQUE(student_id, guardian_id)
);

-- DISCIPLINE table
CREATE TABLE IF NOT EXISTS discipline (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- MENTOR table
CREATE TABLE IF NOT EXISTS mentor (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(50)
);

-- LESSON table
CREATE TABLE IF NOT EXISTS lesson (
    id BIGSERIAL PRIMARY KEY,
    date DATE NOT NULL,
    start_time TIME NOT NULL,
    content TEXT NOT NULL,
    class_status VARCHAR(50) NOT NULL DEFAULT 'SCHEDULED',
    payment_status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    class_type VARCHAR(50) NOT NULL,
    observations TEXT,
    performance_evaluation DOUBLE PRECISION CHECK (performance_evaluation >= 0),
    material_link VARCHAR(1000),
    mentor_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    discipline_id BIGINT NOT NULL,
    CONSTRAINT fk_lesson_mentor FOREIGN KEY (mentor_id) REFERENCES mentor (id) ON DELETE RESTRICT,
    CONSTRAINT fk_lesson_student FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE,
    CONSTRAINT fk_lesson_discipline FOREIGN KEY (discipline_id) REFERENCES discipline (id) ON DELETE RESTRICT
);

-- Indexes for performance
CREATE INDEX idx_lesson_student ON lesson(student_id);
CREATE INDEX idx_lesson_mentor ON lesson(mentor_id);
CREATE INDEX idx_lesson_discipline ON lesson(discipline_id);
CREATE INDEX idx_lesson_date ON lesson(date);
CREATE INDEX idx_student_guardian ON student_guardian(student_id);

-- NOTE: The rule "every student must have at least one guardian" is enforced at the application layer
-- (Bean Validation @NotEmpty on StudentEntity.guardianMappings). A strict DB-level constraint requires
-- triggers in PostgreSQL.
--
-- Enum values in lesson table:
-- class_status: SCHEDULED, COMPLETED, CANCELLED
-- payment_status: PENDING, PAID
-- class_type: ONLINE, IN_PERSON

