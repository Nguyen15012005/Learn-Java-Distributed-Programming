

CREATE (p:Post{code:'P101', name:'Java Basic', description:'abc'});

MATCH (a:Person{name:'Khanh Nguyen'})
MATCH (b:Person{name:'Thi Tran'})
MATCH (p:Post{code:'P101'})
MERGE (a) - [:POSTED] -> (p)
MERGE (b) - [:LIKED] -> (p);

MATCH (a:Person{name:'Khanh Nguyen'})
SET a.age = 40
RETURN a;

MATCH (a:Person{name:'Khanh Nguyen'}) RETURN a;

MATCH (a:Person{name:'Khanh Nguyen'})  DELETE a;

MATCH (a:Person{name:'Khanh Nguyen'})  DETACH DELETE a;


CREATE
  (p1:Person {name: 'Alice', age: 25}),
  (p2:Person {name: 'Bob', age: 30}),
  (p3:Person {name: 'Charlie', age: 22}),
  (p4:Person {name: 'David', age: 35}),
  (p5:Person {name: 'Eve', age: 28}),
  (p6:Person {name: 'Frank', age: 40}),
  (p7:Person {name: 'Grace', age: 27}),
  (p8:Person {name: 'Henry', age: 32}),
  (p9:Person {name: 'Ivy', age: 29}),
  (p10:Person {name: 'Jack', age: 31}),

  (a1:Address {street: '123 Main St', city: 'New York', state: 'NY'}),
  (a2:Address {street: '456 Elm Ave', city: 'London', country: 'UK'}),
  (a3:Address {street: '789 Oak Ln', city: 'Paris', country: 'France'}),
  (a4:Address {street: '101 Pine Ct', city: 'Sydney', state: 'NSW'}),
  (a5:Address {street: '222 Maple Dr', city: 'Rome', country: 'Italy'}),
  (a6:Address {street: '333 Willow Rd', city: 'Berlin', country: 'Germany'}),
  (a7:Address {street: '444 Birch Pl', city: 'Madrid', country: 'Spain'}),
  (a8:Address {street: '555 Cedar Cir', city: 'Tokyo', country: 'Japan'}),
  (a9:Address {street: '666 Redwood St', city: 'Toronto', country: 'Canada'}),
  (a10:Address {street: '777 Oak Ave', city: 'Sao Paulo', country: 'Brazil'}),

  (p1)-[:FRIENDS_WITH]->(p2),
  (p1)-[:FRIENDS_WITH]->(p3),
  (p1)-[:FRIENDS_WITH]->(p6),
  (p2)-[:FRIENDS_WITH]->(p4),
  (p3)-[:FRIENDS_WITH]->(p5),
  (p4)-[:FRIENDS_WITH]->(p6),
  (p4)-[:FRIENDS_WITH]->(p9),
  (p5)-[:FRIENDS_WITH]->(p7),
  (p6)-[:FRIENDS_WITH]->(p8),
  (p7)-[:FRIENDS_WITH]->(p3),
  (p7)-[:FRIENDS_WITH]->(p1),
  (p7)-[:FRIENDS_WITH]->(p9),
  (p7)-[:FRIENDS_WITH]->(p10),
  (p8)-[:FRIENDS_WITH]->(p10),
  (p9)-[:FRIENDS_WITH]->(p1),
  (p9)-[:FRIENDS_WITH]->(p6),
  (p10)-[:FRIENDS_WITH]->(p4),

  (p1)-[:LIVES_AT]->(a1),
  (p2)-[:LIVES_AT]->(a2),
  (p3)-[:LIVES_AT]->(a3),
  (p4)-[:LIVES_AT]->(a4),
  (p5)-[:LIVES_AT]->(a5),
  (p6)-[:LIVES_AT]->(a6),
  (p7)-[:LIVES_AT]->(a7),
  (p8)-[:LIVES_AT]->(a8),
  (p9)-[:LIVES_AT]->(a9),
  (p10)-[:LIVES_AT]->(a10);

//Dem so ban của từng người
MATCH (p:Person) -[:FRIENDS_WITH]-> (f:Person)
WITH p, count(f) as  numOfFriends
RETURN p.name, numOfFriends;

//Liet ke tên của những người bạn của "Alice"
//List comprehension
//[Pattern | Expression]
MATCH (p:Person{name:"Alice"})
RETURN p.name, [(p:Person)-[:FRIENDS_WITH]->(f:Person) | f.name] as friends;

//(p1)-[:FRIENDS_WITH]->(p2), -> p2.name -> Bob
//(p1)-[:FRIENDS_WITH]->(p3), -> p3.name -> Charlie
//(p1)-[:FRIENDS_WITH]->(p6), -> p6.name -> Frank
//--> ['Bob', 'Charlie', 'Frank']

MATCH (p:Person)
WHERE size([(p:Person)-[:FRIENDS_WITH]->(f:Person) | 1]) >=3
RETURN p.name;

//Tim ten cua nhung nguoi ma khong phai ban cua Alice

MATCH (p:Person)
WHERE NOT (p:Person)-[:FRIENDS_WITH]->(:Person {name:'Alice'})
RETURN p.name;

CREATE INDEX person_name_idx IF NOT EXISTS
FOR (p:Person)
ON p.name;

SHOW INDEXES

DROP INDEX person_name_idx


//UNIQUE CONSTRAINT
CREATE CONSTRAINT student_email_uq IF NOT EXISTS
FOR (n:Student)
REQUIRE n.email IS NODE UNIQUE;

//KEY CONSTRAINT
CREATE CONSTRAINT student_pk IF NOT EXISTS
FOR (n:Student)
REQUIRE n.student_id IS NODE KEY;

CREATE CONSTRAINT course_pk IF NOT EXISTS
FOR (n:Course)
REQUIRE n.course_id IS NODE KEY;

CREATE CONSTRAINT dept_pk IF NOT EXISTS
FOR (n:Department)
REQUIRE n.dept_id IS NODE KEY;

SHOW CONSTRAINTS;

CREATE CONSTRAINT student_gpa IF NOT EXISTS
FOR (n:Student)
REQUIRE n.gpa IS :: FLOAT;


//LOAD FROM CSV FILES
LOAD CSV WITH HEADERS
FROM 'file:///courses/courses.csv' AS row
CREATE (c:Course{
    course_id:row.course_id,
    name:row.name,
    hours: toInteger(row.hours)
});

LOAD CSV WITH HEADERS
FROM 'file:///courses/students.csv' AS row
CREATE (s:Student{
  student_id:row.student_id,
  name: row.name,
  gpa:toFloat(row.gpa)
});

LOAD CSV WITH HEADERS
FROM 'file:///courses/departments.csv' AS row
CREATE (d:Department{
  dept_id:row.dept_id,
  name:row.name,
  dean:row.dean,
  building:row.building,
  room:row.room
});

//Tao relationships

LOAD CSV WITH HEADERS
FROM 'file:///courses/courses.csv' AS row
MATCH (c:Course{course_id:row.course_id})
MATCH (d:Department{dept_id:row.dept_id})
MERGE (c) - [:BELONGS_TO] -> (d);

LOAD CSV WITH HEADERS
FROM 'file:///courses/enrollments.csv' AS row
MATCH (c:Course{course_id:row.course_id})
MATCH (s:Student{student_id:row.student_id})
MERGE (s) - [:ENROLLED_IN {semester:row.semester, grade:toInteger(row.grade)}] -> (c);

//course_id,student_id,semester,grade
//CS101,11,"Spring 2024",50
//CS101,11,"Spring 2025",90
//MA101,11,"Winter 2025",70
//CS101,12,"Summer 2025",100
//CS201,22,"Spring 2025",90
//MA201,33,"Spring 2025",90
//EE102,33,"Winter 2025",70
//MA301,22,"Summer 2025",60



//course_id,name,hours,dept_id
//CS101,Programming,4,CS
//CS201,Algorithms,3,CS
//CS202,Systems,3,CS
//MA101,Algebra,3,Math
//MA201,Calculus,4,Math
//MA301,Analysis,4,Math
//MU104,Jazz,3,Music
//EE102,Circuits,3,EE
//IE101,Proabability,3,IE
//IE102,Statistics,3,IE
