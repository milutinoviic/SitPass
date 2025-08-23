INSERT INTO facility (
    id, name, description, created_at, address, city, total_rating, active, is_deleted
) VALUES (
             1,
             'Fitnes Centar Arena',
             'Savremeni fitnes centar sa teretanom i grupnim treninzima',
             '2025-08-01',
             'Bulevar Oslobodjenja 100',
             'Novi Sad',
             4.8,
             true,
             false
         );

INSERT INTO work_day (
    id, valid_from, day, start_time, end_time, is_deleted, facility_id
) VALUES
      (101, '2025-08-11', 'MONDAY',   '2025-08-11 08:00:00', '2025-08-11 16:00:00', false, 1),
      (102, '2025-08-12', 'TUESDAY',  '2025-08-12 08:00:00', '2025-08-12 16:00:00', false, 1),
      (103, '2025-08-13', 'WEDNESDAY','2025-08-13 08:00:00', '2025-08-13 16:00:00', false, 1),
      (104, '2025-08-16', 'SATURDAY', '2025-08-16 09:00:00', '2025-08-16 13:00:00', false, 1),
      (105, '2025-08-17', 'SUNDAY',   '2025-08-17 10:00:00', '2025-08-17 14:00:00', false, 1);

INSERT INTO work_day (
    id, valid_from, day, start_time, end_time, is_deleted, facility_id
) VALUES
      (106, '2025-08-21', 'THURSDAY', '2025-08-21 08:00:00', '2025-08-21 16:00:00', false, 1),
      (107, '2025-08-22', 'FRIDAY',   '2025-08-22 08:00:00', '2025-08-22 16:00:00', false, 1);


INSERT INTO work_day (
    id, valid_from, day, start_time, end_time, is_deleted, facility_id
) VALUES
      (108, '2025-08-04', 'MONDAY',    '2025-08-04 08:00:00', '2025-08-04 16:00:00', false, 1),
      (109, '2025-08-05', 'TUESDAY',   '2025-08-05 08:00:00', '2025-08-05 16:00:00', false, 1),
      (110, '2025-08-06', 'WEDNESDAY', '2025-08-06 08:00:00', '2025-08-06 16:00:00', false, 1),
      (111, '2025-08-09', 'SATURDAY',  '2025-08-09 09:00:00', '2025-08-09 13:00:00', false, 1),
      (112, '2025-08-10', 'SUNDAY',    '2025-08-10 10:00:00', '2025-08-10 14:00:00', false, 1);

INSERT INTO work_day (
    id, valid_from, day, start_time, end_time, is_deleted, facility_id
) VALUES
      (113, '2025-08-25', 'MONDAY',    '2025-08-25 08:00:00', '2025-08-25 16:00:00', false, 1),
      (114, '2025-08-26', 'TUESDAY',   '2025-08-26 08:00:00', '2025-08-26 16:00:00', false, 1),
      (115, '2025-08-27', 'WEDNESDAY', '2025-08-27 08:00:00', '2025-08-27 16:00:00', false, 1),
      (116, '2025-08-30', 'SATURDAY',  '2025-08-30 09:00:00', '2025-08-30 13:00:00', false, 1),
      (117, '2025-08-31', 'SUNDAY',    '2025-08-31 10:00:00', '2025-08-31 14:00:00', false, 1);

INSERT INTO work_day (
    valid_from, day, start_time, end_time, is_deleted, facility_id
) VALUES (
             '2025-05-16',
             'FRIDAY',
             '2025-05-16 08:00:00',
             '2025-05-16 16:00:00',
             false,
             1
         );


INSERT INTO discipline (id, name, is_deleted) VALUES
                                                  (1, 'Yoga', false),
                                                  (2, 'Pilates', false),
                                                  (3, 'Crossfit', false),
                                                  (4, 'Zumba', false),
                                                  (5, 'Spinning', false);
