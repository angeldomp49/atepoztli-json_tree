# Value Retrieval

The `JSONLeaf` interface has two specialized child interfaces for value retrieval: `JSONStringKeyLeaf` and
`JSONIndexKeyLeaf`. These interfaces provide a convenient `asLeaf` method to access nested values with automatic type wrapping.

---

## English

### Retrieving from `ObjectLeaf` (by key)

`ObjectLeaf` implements `JSONStringKeyLeaf`, allowing you to retrieve values by their string key.

#### Usage

The `asLeaf(String key)` method returns an `Optional<JSONLeaf>` containing the value wrapped in the appropriate leaf type if the key exists.

#### Examples

Here is an example of how to retrieve different primitive types from an `ObjectLeaf`:

```java
import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObjectLeafRetrievalTest {

    @Test
    void testRetrieveValuesFromObjectLeaf() {
        var objectLeaf = ObjectLeafBuilder.builder()
                .put("name", "John Doe")
                .put("age", 30)
                .put("isStudent", false)
                .build();

        var nameLeaf = objectLeaf.asLeaf("name");
        var ageLeaf = objectLeaf.asLeaf("age");
        var isStudentLeaf = objectLeaf.asLeaf("isStudent");

        assertTrue(nameLeaf.isPresent());
        assertEquals("\"John Doe\"", nameLeaf.get().getLeafValue());

        assertTrue(ageLeaf.isPresent());
        assertEquals("30", ageLeaf.get().getLeafValue());

        assertTrue(isStudentLeaf.isPresent());
        assertEquals("false", isStudentLeaf.get().getLeafValue());
    }
}
```

### Retrieving from Array Leaves (by index)

Array-based leaves like `ArrayStringLeaf`, `ArrayNumberLeaf`, `ArrayBooleanLeaf`, and `ArrayObjectLeaf` implement
`JSONIndexKeyLeaf`. This allows you to retrieve values by their numeric index.

#### Usage

The `asLeaf(int index)` method returns an `Optional<JSONLeaf>` containing the value wrapped in the appropriate leaf type if the index is valid.

#### Examples

Here are examples of retrieving values from different types of array leaves:

**`ArrayStringLeaf`**

```java
import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.builders.ArrayStringLeafBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayStringLeafRetrievalTest {

    @Test
    void testRetrieveValueFromArray() {
        var arrayLeaf = ArrayStringLeafBuilder.builder()
                .add("Apple")
                .add("Banana")
                .add("Cherry")
                .build();

        var fruitLeaf = arrayLeaf.asLeaf(1);

        assertTrue(fruitLeaf.isPresent());
        assertEquals("\"Banana\"", fruitLeaf.get().getLeafValue());
    }
}
```

**`ArrayNumberLeaf`**

```java
import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.builders.ArrayNumberLeafBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayNumberLeafRetrievalTest {

    @Test
    void testRetrieveValueFromArray() {
        var arrayLeaf = ArrayNumberLeafBuilder.builder()
                .add(10)
                .add(20.5)
                .add(30L)
                .build();

        var numberLeaf = arrayLeaf.asLeaf(1);

        assertTrue(numberLeaf.isPresent());
        assertEquals("20.5", numberLeaf.get().getLeafValue());
    }
}
```

**`ArrayBooleanLeaf`**

```java
import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.builders.ArrayBooleanLeafBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayBooleanLeafRetrievalTest {

    @Test
    void testRetrieveValueFromArray() {
        var arrayLeaf = ArrayBooleanLeafBuilder.builder()
                .add(true)
                .add(false)
                .add(true)
                .build();

        var booleanLeaf = arrayLeaf.asLeaf(1);

        assertTrue(booleanLeaf.isPresent());
        assertEquals("false", booleanLeaf.get().getLeafValue());
    }
}
```

---

## Spanish

### Recuperación desde `ObjectLeaf` (por clave)

`ObjectLeaf` implementa `JSONStringKeyLeaf`, lo que permite recuperar valores por su clave de tipo `String`.

#### Uso

El método `asLeaf(String key)` devuelve un `Optional<JSONLeaf>` que contiene el valor si la clave existe y el
tipo coincide.

#### Ejemplos

A continuación, se muestra un ejemplo de cómo recuperar diferentes tipos primitivos desde un `ObjectLeaf`:

```java
import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObjectLeafRetrievalTest {

    @Test
    void testRetrieveValuesFromObjectLeaf() {
        var objectLeaf = ObjectLeafBuilder.builder()
                .put("name", "John Doe")
                .put("age", 30)
                .put("isStudent", false)
                .build();

        var nameLeaf = objectLeaf.asLeaf("name");
        var ageLeaf = objectLeaf.asLeaf("age");
        var isStudentLeaf = objectLeaf.asLeaf("isStudent");

        assertTrue(nameLeaf.isPresent());
        assertEquals("\"John Doe\"", nameLeaf.get().getLeafValue());

        assertTrue(ageLeaf.isPresent());
        assertEquals("30", ageLeaf.get().getLeafValue());

        assertTrue(isStudentLeaf.isPresent());
        assertEquals("false", isStudentLeaf.get().getLeafValue());
    }
}
```

### Recuperación desde Hojas de Array (por índice)

Las hojas basadas en arrays como `ArrayStringLeaf`, `ArrayNumberLeaf`, `ArrayBooleanLeaf` y `ArrayObjectLeaf`
implementan `JSONIndexKeyLeaf`. Esto permite recuperar valores por su índice numérico.

#### Uso

El método `asLeaf(int index)` devuelve un `Optional<JSONLeaf>` que contiene el valor si el índice es válido y el
tipo es correcto. Para tipos primitivos, este será una clase contenedora como `StringJSONLeaf`, `NumberJSONLeaf` o

#### Ejemplos

A continuación, se muestran ejemplos de cómo recuperar valores desde diferentes tipos de hojas de array:

**`ArrayStringLeaf`**

```java
import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.builders.ArrayStringLeafBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayStringLeafRetrievalTest {

    @Test
    void testRetrieveValueFromArray() {
        var arrayLeaf = ArrayStringLeafBuilder.builder()
                .add("Manzana")
                .add("Banana")
                .add("Cereza")
                .build();

        var fruitLeaf = arrayLeaf.asLeaf(1);

        assertTrue(fruitLeaf.isPresent());
        assertEquals("\"Banana\"", fruitLeaf.get().getLeafValue());
    }
}
```

**`ArrayNumberLeaf`**

```java
import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.builders.ArrayNumberLeafBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayNumberLeafRetrievalTest {

    @Test
    void testRetrieveValueFromArray() {
        var arrayLeaf = ArrayNumberLeafBuilder.builder()
                .add(10)
                .add(20.5)
                .add(30L)
                .build();

        var numberLeaf = arrayLeaf.asLeaf(1);

        assertTrue(numberLeaf.isPresent());
        assertEquals("20.5", numberLeaf.get().getLeafValue());
    }
}
```

**`ArrayBooleanLeaf`**

```java
import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.builders.ArrayBooleanLeafBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayBooleanLeafRetrievalTest {

    @Test
    void testRetrieveValueFromArray() {
        var arrayLeaf = ArrayBooleanLeafBuilder.builder()
                .add(true)
                .add(false)
                .add(true)
                .build();

        var booleanLeaf = arrayLeaf.asLeaf(1);

        assertTrue(booleanLeaf.isPresent());
        assertEquals("false", booleanLeaf.get().getLeafValue());
    }
}
```

---

## French

### Récupération depuis `ObjectLeaf` (par clé)

`ObjectLeaf` implémente `JSONStringKeyLeaf`, ce qui permet de récupérer des valeurs par leur clé de type `String`.

#### Utilisation

La méthode `asLeaf(String key)` renvoie un `Optional<JSONLeaf>` contenant la valeur si la clé existe et que le
type correspond.

#### Exemples

Voici un exemple de récupération de différents types primitifs à partir d'un `ObjectLeaf`:

```java
import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.builders.ObjectLeafBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObjectLeafRetrievalTest {

    @Test
    void testRetrieveValuesFromObjectLeaf() {
        var objectLeaf = ObjectLeafBuilder.builder()
                .put("name", "John Doe")
                .put("age", 30)
                .put("isStudent", false)
                .build();

        var nameLeaf = objectLeaf.asLeaf("name");
        var ageLeaf = objectLeaf.asLeaf("age");
        var isStudentLeaf = objectLeaf.asLeaf("isStudent");

        assertTrue(nameLeaf.isPresent());
        assertEquals("\"John Doe\"", nameLeaf.get().getLeafValue());

        assertTrue(ageLeaf.isPresent());
        assertEquals("30", ageLeaf.get().getLeafValue());

        assertTrue(isStudentLeaf.isPresent());
        assertEquals("false", isStudentLeaf.get().getLeafValue());
    }
}
```

### Récupération depuis les Feuilles de Tableau (par index)

Les feuilles basées sur des tableaux comme `ArrayStringLeaf`, `ArrayNumberLeaf`, `ArrayBooleanLeaf` et `ArrayObjectLeaf`
implémentent `JSONIndexKeyLeaf`. Cela permet de récupérer des valeurs par leur index numérique.

#### Utilisation

La méthode `asLeaf(int index)` renvoie un `Optional<JSONLeaf>` contenant la valeur si l'index est valide et que
le type est correct. Pour les types primitifs, ce sera une classe d'emballage comme `StringJSONLeaf`, `NumberJSONLeaf`
ou `BooleanJSONLeaf`.

#### Exemples

Voici des exemples de récupération de valeurs à partir de différents types de feuilles de tableau:

**`ArrayStringLeaf`**

```java
import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.builders.ArrayStringLeafBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayStringLeafRetrievalTest {

    @Test
    void testRetrieveValueFromArray() {
        var arrayLeaf = ArrayStringLeafBuilder.builder()
                .add("Pomme")
                .add("Banane")
                .add("Cerise")
                .build();

        var fruitLeaf = arrayLeaf.asLeaf(1);

        assertTrue(fruitLeaf.isPresent());
        assertEquals("\"Banane\"", fruitLeaf.get().getLeafValue());
    }
}
```

**`ArrayNumberLeaf`**

```java
import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.builders.ArrayNumberLeafBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayNumberLeafRetrievalTest {

    @Test
    void testRetrieveValueFromArray() {
        var arrayLeaf = ArrayNumberLeafBuilder.builder()
                .add(10)
                .add(20.5)
                .add(30L)
                .build();

        var numberLeaf = arrayLeaf.asLeaf(1);

        assertTrue(numberLeaf.isPresent());
        assertEquals("20.5", numberLeaf.get().getLeafValue());
    }
}
```

**`ArrayBooleanLeaf`**

```java
import org.junit.jupiter.api.Test;
import org.makechtec.software.json_tree.builders.ArrayBooleanLeafBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayBooleanLeafRetrievalTest {

    @Test
    void testRetrieveValueFromArray() {
        var arrayLeaf = ArrayBooleanLeafBuilder.builder()
                .add(true)
                .add(false)
                .add(true)
                .build();

        var booleanLeaf = arrayLeaf.asLeaf(1);

        assertTrue(booleanLeaf.isPresent());
        assertEquals("false", booleanLeaf.get().getLeafValue());
    }
}
```

