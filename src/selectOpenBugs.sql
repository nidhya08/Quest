DECLARE from_date date;
--date formate 'yyyymmdd'
DECLARE to_date date;
BEGIN
SET from_date='<yyyymmdd>';
SET to_date='<yyyymmdd>';
for i in from_date..to_date loop
select id,open_date,close_date,severity from bugs where open_date <= i and close_date > i;
END loop;
END;

