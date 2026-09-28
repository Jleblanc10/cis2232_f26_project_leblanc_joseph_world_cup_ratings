# For hccis.ca version of the database
# DROP DATABASE IF EXISTS cis2232_player_effectiveness_ratings;
# CREATE DATABASE cis2232_player_effectiveness_ratings;
# use cis2232_player_effectiveness_ratings;

# Changed Database Name and changed the variables to fit the
# Player effectiveness rating template Joseph LeBlanc
# Changed the generated data as well. Joseph LeBlanc

#For localhost
DROP DATABASE IF EXISTS cis2232_player_effectiveness_ratings;
CREATE DATABASE cis2232_player_effectiveness_ratings;
use cis2232_player_effectiveness_ratings;


CREATE TABLE PlayerEffectivenessRatings
(
    id                int(5),
    assessmentDate    varchar(10) NOT NULL COMMENT 'yyyy-MM-dd',
    createdDateTime   varchar(20) NOT NULL COMMENT 'yyyy-MM-dd hh:mm:ss',
    playerName       varchar(50) NOT NULL COMMENT 'Players name',
    country      varchar(50) NOT NULL COMMENT 'Players country',
    position    int(5) COMMENT 'Players position',
    minutesPlayed    int(5) COMMENT 'Minutes played',
    goals int(5) COMMENT 'Number of goals',
    assists int(5) COMMENT 'Number of assists',
    defensiveActions int(5) COMMENT 'Number of defensive actions',
    yellowCards int(5) COMMENT 'Number of yellow cards',
    redCards    int(5) COMMENT 'Number of red cards',
    playerEffectiveness Double(10,2) COMMENT 'Calculated effectiveness score'
) COMMENT 'This table holds player performance details';

ALTER TABLE PlayerEffectivenessRatings
    ADD PRIMARY KEY (id);
ALTER TABLE PlayerEffectivenessRatings
    MODIFY id int(4) NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key',
    AUTO_INCREMENT = 1;

-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-22',  '2022-08-20 11:35:15','Maria Smith','Canada',     11,    5, 14,    78,    6, 0, 0, 1085);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-11',  '2022-08-11 11:35:15','Rhonda Jones','Canada',    5, 7, 4, 36,    5, 0, 0, 622);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-07', '2022-08-07 11:35:15','Chad Collins','Canada',      8, 8, 4, 37,    5, 0, 0, 707);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-07', '2022-08-07 11:35:15','Rhonda Jones','Canada',      12,    8, 9, 53,    4, 0, 0, 879);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-07', '2022-08-05 11:35:15','Chad Collins','Canada',      8, 10,    7, 52,    3, 0, 0, 740);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-08',  '2022-08-08 11:35:15','Rhonda Jones','Canada',    10,    8, 8, 61,    6, 0, 0, 972);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-10',  '2022-08-10 11:35:15','Chad Collins','Canada',    17,    14,    8, 70,    13,    0, 0, 1403);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-08',  '2022-08-08 11:35:15','Maria Smith','Canada',     17,    18,    12,    77,    8, 0, 0, 1385);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-22',  '2022-08-20 11:35:15','Chad Collins','Canada',    14,    11,    10,    86,    16, 0, 0, 1448);

INSERT INTO PlayerEffectivenessRatings (id, assessmentDate, createdDateTime, playerName, country,
                                             position, minutesPlayed, goals, assists,
                                             defensiveActions, yellowCards, redCards,playerEffectiveness)
VALUES (1, '2026-05-24', '2026-05-24 18:00:00', 'James Garner', 'England',     2,    3413, 2, 7,    72,    12, 0, 37.25),
       (2, '2026-05-24', '2026-05-24 18:00:00', 'João Gomes', 'Brazil',    8, 2829, 1, 1, 68,    10, 0, 30.75),
       (3, '2026-05-24', '2026-05-24 18:00:00', 'Mateus Fernandes', 'Portugal',      8, 3018, 3, 4, 67,    7, 0, 36.00),
       (4, '2026-05-24', '2026-05-24 18:00:00', 'Tyrick Mitchell', 'England',      3,    2938,    1, 2, 65,    5, 0, 32.50),
       (5, '2026-05-24', '2026-05-24 18:00:00', 'Elliot Anderson', 'England',      8, 3331,    4, 4, 60,    8, 0, 33.00),
       (6, '2026-05-24', '2026-05-24 18:00:00', 'João Palhinha', 'Portugal',    6,    2199,    5, 2, 61,    8, 0, 33.00),
       (7, '2026-05-24', '2026-05-24 18:00:00', 'Moisés Caicedo', 'Ecuador',    6,    2796,    3, 1, 47,    11,    0, 19.75),
       (8, '2026-05-24', '2026-05-24 18:00:00', 'Neco Williams', 'Wales',     2,    3204,    2, 3,    61,    6, 1, 29.75),
       (9, '2026-05-24', '2026-05-24 18:00:00', 'Diego Gómez', 'Paraguay',    8,    2120,    5, 1,    49,    9, 1, 23.75);



CREATE TABLE CodeType (codeTypeId int(3) COMMENT 'This is the primary key for code types',
                       englishDescription varchar(100) NOT NULL COMMENT 'English description',
                       frenchDescription varchar(100) DEFAULT NULL COMMENT 'French description',
                       createdDateTime datetime DEFAULT NULL,
                       createdUserId varchar(20) DEFAULT NULL,
                       updatedDateTime datetime DEFAULT NULL,
                       updatedUserId varchar(20) DEFAULT NULL
) COMMENT 'This tables holds the code types that are available for the application';

ALTER TABLE CodeType
    ADD PRIMARY KEY (CodeTypeId);

INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (1, 'User Types', 'User Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');
INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (2, 'Player Types', 'Player Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');



CREATE TABLE CodeValue (
                           codeTypeId int(3) NOT NULL COMMENT 'see code_type table',
                           codeValueSequence int(3) NOT NULL,
                           englishDescription varchar(100) NOT NULL COMMENT 'English description',
                           englishDescriptionShort varchar(20) NOT NULL COMMENT 'English abbreviation for description',
                           frenchDescription varchar(100) DEFAULT NULL COMMENT 'French description',
                           frenchDescriptionShort varchar(20) DEFAULT NULL COMMENT 'French abbreviation for description',
                           sortOrder int(3) DEFAULT NULL COMMENT 'Sort order if applicable',
                           createdDateTime datetime DEFAULT NULL,
                           createdUserId varchar(20) DEFAULT NULL,
                           updatedDateTime datetime DEFAULT NULL,
                           updatedUserId varchar(20) DEFAULT NULL
) COMMENT='This will hold code values for the application.';

ALTER TABLE CodeValue
    ADD PRIMARY KEY (CodeTypeId, codeValueSequence);

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (1, 1, 'General', 'General', 'GeneralFR', 'GeneralFR', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (1, 2, 'Admin', 'Admin', 'Admin', 'Admin', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (2, 1, 'Position', 'Position', 'PositionFR', 'PositionFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (2, 2, 'Minutes Played', 'Minutes', 'Minutes PlayedFR', 'MinutesFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (2, 3, 'Goals', 'Goals', 'GoalsFR', 'GoalsFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (2, 4, 'Assists', 'Assists', 'AssistsFR', 'AssistsFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (2, 5, 'Defensive Actions', 'Defense', 'Defensive ActionsFR', 'DefenseFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (2, 6, 'Yellow Cards', 'Yellow', 'Yellow CardsFR', 'YellowFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (2, 7, 'Red Cards', 'Red', 'Red CardsFR', 'RedFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');