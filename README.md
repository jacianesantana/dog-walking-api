# Dog Walking
Oferece serviço de passeio para cães.

## Desafio de Código da empresa DogHero

- Apenas a entidade Dog Walking é obrigatória e deve conter: status, data de agendamento, preço, duração (30 ou 60 min),
latitude, longitude, pets, horário de início e término;
- Criar uma API para Dog Walking com index, show, create, start_walk e finish_walk;
- A API de index deve receber um filtro através de uma flag para retornar apenas os próximos passeios a partir de hoje ou todos.
- A API para criação de passeio deve receber todos os atributos listados acima menos status;
- A API de show deve retornar a duração real do passeio, ou seja, a diferença entre o início e o término;
- O preço é calculado dinamicamente:
Um passeio de 30 minutos para 1 cachorro custa R$25, sendo cada cachorro adicional R$15;
Um passeio de 60 minutos para 1 cachorro custa R$35, sendo cada cachorro adicional R$20.

## Stack

- Java 11
- Spring Boot
- Lombok
- Junit
