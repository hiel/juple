# Juple

Tuple for Java

# Installation

## Gradle

Add the following code to your root `build.gradle`
```
repositories {
    ...
    maven { url 'https://jitpack.io' }
}
dependencies {
    ...
    implementation 'com.github.hiel:juple:{latest_version}'
}
```

# Usage

```java
import org.juple.Tuple;
```

```java
Tuple tuple = new Tuple(1, 2);

Tuple tuple = tuple.fromList(List.of(1, 2));

Tuple tuple1 = new Tuple(1, 2);
Tuple tuple2 = new Tuple(3);
// Tuple(1, 2), Tuple(3), 4
Tuple tuple = new Tuple(tuple1, tuple2, 4);
```

```java
Tuple tuple = new Tuple(1, 1, "2");

// ArrayList<Object>(1, 1, "2")
Tuple new_tuple = tuple.toArrayList();

// {1, 1, "2"}
Tuple new_tuple = tuple.toArray();

// "2"
Object object = tuple.get(2);

// 1, 1
Tuple tuple = tuple.subTuple(0, 2);

// 2
int index = tuple.indexOf("2");

// 1
int index = tuple.lastIndexOf(1);

// false
boolean isEmpty = tuple.isEmpty();

// 3
int size = tuple.size();

// true
boolean bool = tuple.contains(1);

// 2
int count = tuple.count(1);

// "(1, 1, 2)"
String string = tuple.toString();

tuple.forEach((v) -> log(v));
```
