CREATE DATABASE IF NOT EXISTS detection_platform;
USE detection_platform;

CREATE TABLE IF NOT EXISTS mitre_technique (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    technique_id  VARCHAR(10)  NOT NULL UNIQUE,
    name          VARCHAR(255) NOT NULL,
    tactic        VARCHAR(100) NOT NULL,
    description   TEXT,
    mitre_url     VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS detection_test (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    technique_id  INT          NOT NULL,
    test_type     ENUM('CUSTOM', 'ART') NOT NULL DEFAULT 'CUSTOM',
    description   TEXT,
    yaml_content  TEXT,
    platform      VARCHAR(100),
    created_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (technique_id) REFERENCES mitre_technique(id)
);

CREATE TABLE IF NOT EXISTS test_run (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    test_id         INT          NOT NULL,
    run_date        DATE         NOT NULL,
    result          ENUM('DETECTED', 'NOT_DETECTED', 'PARTIAL') NOT NULL,
    detection_tool  VARCHAR(100),
    notes           TEXT,
    screenshot_path VARCHAR(255),
    logged_at       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (test_id) REFERENCES detection_test(id)
);

-- Pre-populated med MITRE-teknikker fra min praktik hos CIP
INSERT IGNORE INTO mitre_technique (technique_id, name, tactic, description, mitre_url) VALUES
('T1016',     'System Network Configuration Discovery', 'Discovery',        'Kortlæg netværkskonfigurationer på et kompromitteret system.',          'https://attack.mitre.org/techniques/T1016/'),
('T1025',     'Data from Removable Media',              'Collection',       'Tilgå og eksfiltrere data fra flytbare medier som USB-enheder.',         'https://attack.mitre.org/techniques/T1025/'),
('T1041',     'Exfiltration Over C2 Channel',           'Exfiltration',     'Send stjålne data ud af netværket via en eksisterende C2-kanal.',        'https://attack.mitre.org/techniques/T1041/'),
('T1654',     'Log Enumeration',                        'Discovery',        'Gennemgå systemlogs for at kortlægge miljøet eller undgå detektion.',    'https://attack.mitre.org/techniques/T1654/'),
('T1059.001', 'PowerShell',                             'Execution',        'Brug af PowerShell til at eksekvere kommandoer og scripts.',             'https://attack.mitre.org/techniques/T1059/001/'),
('T1053.005', 'Scheduled Task',                         'Execution',        'Oprettelse af planlagte opgaver til at eksekvere kode.',                 'https://attack.mitre.org/techniques/T1053/005/'),
('T1047',     'Windows Management Instrumentation',     'Execution',        'Brug af WMI til at eksekvere kode og kommandoer.',                      'https://attack.mitre.org/techniques/T1047/'),
('T1218.005', 'Mshta',                                  'Defense Evasion',  'Brug af mshta.exe til at eksekvere HTA-filer og scripts.',              'https://attack.mitre.org/techniques/T1218/005/');
