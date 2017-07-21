
    alter table Mutation 
        drop 
        foreign key FK3772F6C9A47D2DEF;

    alter table MutationTestResult_Errors 
        drop 
        foreign key FKC0E8A2B240216BB7;

    alter table MutationTestResult_Errors 
        drop 
        foreign key FKC0E8A2B2D25D5A1D;

    alter table MutationTestResult_Passing 
        drop 
        foreign key FK851DA3CA40216BB7;

    alter table MutationTestResult_Passing 
        drop 
        foreign key FK851DA3CAD25D5A1D;

    alter table MutationTestResult_TestMessage 
        drop 
        foreign key FK67A12C2ED25D5A1D;

    alter table MutationTestResult_TestMessage 
        drop 
        foreign key FK67A12C2EC1D2AA43;

    alter table ViolatedInvariants 
        drop 
        foreign key FKC4306A552615D278;

    drop table if exists Mutation;

    drop table if exists MutationTestResult;

    drop table if exists MutationTestResult_Errors;

    drop table if exists MutationTestResult_Passing;

    drop table if exists MutationTestResult_TestMessage;

    drop table if exists TestMessage;

    drop table if exists TestName;

    drop table if exists ViolatedInvariants;

    create table Mutation (
        id bigint not null auto_increment,
        addInfo varchar(255),
        className varchar(255),
        lineNumber integer not null,
        methodName varchar(255),
        mutationForLine integer not null,
        mutationType integer,
        mutationResult_id bigint,
        primary key (id),
        unique (className, lineNumber, mutationForLine, mutationType)
    ) ENGINE=InnoDB;

    create table MutationTestResult (
        id bigint not null auto_increment,
        date datetime,
        differentViolatedInvariants integer not null,
        runs integer not null,
        totalViolations integer not null,
        touched bit not null,
        primary key (id)
    ) ENGINE=InnoDB;

    create table MutationTestResult_Errors (
        mutationTestResult_id bigint not null,
        testMessage_id bigint not null,
        error_id integer not null,
        primary key (mutationTestResult_id, error_id),
        unique (testMessage_id)
    ) ENGINE=InnoDB;

    create table MutationTestResult_Passing (
        mutationTestResult_id bigint not null,
        testMessage_id bigint not null,
        passing_id integer not null,
        primary key (mutationTestResult_id, passing_id),
        unique (testMessage_id)
    ) ENGINE=InnoDB;

    create table MutationTestResult_TestMessage (
        MutationTestResult_id bigint not null,
        failures_id bigint not null,
        failure_list_id integer not null,
        primary key (MutationTestResult_id, failure_list_id),
        unique (failures_id)
    ) ENGINE=InnoDB;

    create table TestMessage (
        id bigint not null auto_increment,
        duration bigint not null,
        hasTouched bit not null,
        message text,
        testCaseName varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table TestName (
        id bigint not null auto_increment,
        duration bigint not null,
        name varchar(1000),
        project varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table ViolatedInvariants (
        result_id bigint not null,
        violatedInvariant integer,
        violated_index integer not null,
        primary key (result_id, violated_index)
    ) ENGINE=InnoDB;

    alter table Mutation 
        add index FK3772F6C9A47D2DEF (mutationResult_id), 
        add constraint FK3772F6C9A47D2DEF 
        foreign key (mutationResult_id) 
        references MutationTestResult (id);

    alter table MutationTestResult_Errors 
        add index FKC0E8A2B240216BB7 (testMessage_id), 
        add constraint FKC0E8A2B240216BB7 
        foreign key (testMessage_id) 
        references TestMessage (id);

    alter table MutationTestResult_Errors 
        add index FKC0E8A2B2D25D5A1D (mutationTestResult_id), 
        add constraint FKC0E8A2B2D25D5A1D 
        foreign key (mutationTestResult_id) 
        references MutationTestResult (id);

    alter table MutationTestResult_Passing 
        add index FK851DA3CA40216BB7 (testMessage_id), 
        add constraint FK851DA3CA40216BB7 
        foreign key (testMessage_id) 
        references TestMessage (id);

    alter table MutationTestResult_Passing 
        add index FK851DA3CAD25D5A1D (mutationTestResult_id), 
        add constraint FK851DA3CAD25D5A1D 
        foreign key (mutationTestResult_id) 
        references MutationTestResult (id);

    alter table MutationTestResult_TestMessage 
        add index FK67A12C2ED25D5A1D (MutationTestResult_id), 
        add constraint FK67A12C2ED25D5A1D 
        foreign key (MutationTestResult_id) 
        references MutationTestResult (id);

    alter table MutationTestResult_TestMessage 
        add index FK67A12C2EC1D2AA43 (failures_id), 
        add constraint FK67A12C2EC1D2AA43 
        foreign key (failures_id) 
        references TestMessage (id);

    alter table ViolatedInvariants 
        add index FKC4306A552615D278 (result_id), 
        add constraint FKC4306A552615D278 
        foreign key (result_id) 
        references MutationTestResult (id);
