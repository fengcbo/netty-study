namespace java com.shiluns.netty.thrift
namespace py py.thrift.generated

typedef i32 int
typedef i16 short
typedef i64 long
typedef bool boolean
typedef string String


struct Person {
    1: optional String name,
    2: optional int age,
    3: optional boolean married
}

exception DataException {
    1: optional String message,
    2: optional String callStack,
    3: optional String date
}


service PersonService {

    Person getPersonByUserName(1: String name) throws (1: DataException dataException),

    void savePerson(1: Person person),

}
