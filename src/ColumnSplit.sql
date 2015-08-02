CREATE PROCEDURE splitColumn [id, name]
IS
@ID int;
@name varchar(100);
index int;
pivot int;
count int;
BEGIN
DECLARE @temp TABLE
(
id_temp int,
name varchar(50);
)
SET @index = 1;
SET @pivot = 1;
SET @count = 0;
WHILE (@index< LEN(@name)+1)
BEGIN
	WHILE(SUBSTRING(@name,@pivot,1)!='|')
	BEGIN
		@pivot +=1;
		@count +=1;
	END
	INSERT INTO @temp (id_temp,name) VALUES (id,SUBSTRING(@name,@index,@count));
	@index += @pivot;
	@count = 0;
END
SELECT id_temp, name FROM @temp;
END
END

DECLARE curSplit CURSOR FOR 
SELECT ID, NAME FROM TABLE1
OPEN curSplit
FETCH NEXT FROM curSplit into @ID, @name

WHILE @@FETCH_STATUS = 0 BEGIN
	EXEC splitColumn @id, @name
	FETCH NEXT FROM curSplit into @ID, @name
END

CLOSE curSplit
DEALLOCATE curSplit