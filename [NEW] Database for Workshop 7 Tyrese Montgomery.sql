Create table Trainer (
trainerID INT AUTO_INCREMENT PRIMARY KEY ,
Name VARCHAR (100) not Null,
Region VARCHAR (50); 
insert into trainer (name,Region) 
values ('Ash ketchum','kanto');

insert into trainer ( name,Region)
values ('Misty', 'cerulean');

select * from trainer ;

create table Pokemon(
  PokemonID INT AUTO_INCREMENT PRIMARY KEY,
  Name VARCHAR (100) NOT NULL,
  TYPE VARCHAR (50),
  Level INT ,
  TrainerID INT,
  FOREIGN KEY (TrainerID) REFERENCES Trainer(TrainerID)
  );
  
  insert into pokemon (name, type,level , trainerid)
  values ('pikachu','electric',25,1),
		('bulbasuar','grass',15,1),
        ('psyduck','water',18,2);
select * from pokemon;
DELETE FROM Pokemon
WHERE PokemonID=3;

update pokemon 
set level = level + 1
Where pokemonid=1 ;