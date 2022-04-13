package services;

import models.Symbol;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constants {
    public static Map<Symbol,Integer> symbolPriorityMap =
            Stream.of(
                            new AbstractMap.SimpleImmutableEntry<>(Symbol.EMPTY, 0),
                            new AbstractMap.SimpleImmutableEntry<>(Symbol.X, 1),
                            new AbstractMap.SimpleImmutableEntry<>(Symbol.O, 2)
                    )
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
}
