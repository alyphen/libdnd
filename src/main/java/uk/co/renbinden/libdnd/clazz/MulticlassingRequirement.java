package uk.co.renbinden.libdnd.clazz;

import uk.co.renbinden.libdnd.ability.Ability;
import uk.co.renbinden.libdnd.character.DndCharacter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public interface MulticlassingRequirement {

    final class AnyRequirement implements MulticlassingRequirement {

        private final List<MulticlassingRequirement> requirements;

        public AnyRequirement(MulticlassingRequirement... requirements) {
            this.requirements = List.of(requirements);
        }

        public List<MulticlassingRequirement> getRequirements() {
            return requirements;
        }

        @Override
        public String[] printRequirements() {
            return printRequirements(0);
        }

        @Override
        public String[] printRequirements(int deep) {
            StringBuilder prefix = new StringBuilder(" ");
            for (int i = 0; i < deep; i++) {
                prefix.append("* ");
            }
            return Stream.of(
                    "Any of:",
                    requirements.stream()
                            .flatMap(requirement -> Arrays.stream(requirement.printRequirements(deep + 1)))
                            .toArray(String[]::new)
            ).map(line -> prefix.toString() + line).toArray(String[]::new);
        }

        @Override
        public boolean meets(DndCharacter character) {
            return requirements.stream().anyMatch(requirement -> requirement.meets(character));
        }

    }

    final class AllRequirement implements MulticlassingRequirement {

        private final List<MulticlassingRequirement> requirements;

        public AllRequirement(MulticlassingRequirement... requirements) {
            this.requirements = List.of(requirements);
        }

        public List<MulticlassingRequirement> getRequirements() {
            return requirements;
        }

        @Override
        public String[] printRequirements() {
            return printRequirements(0);
        }

        @Override
        public String[] printRequirements(int deep) {
            StringBuilder prefix = new StringBuilder(" ");
            for (int i = 0; i < deep; i++) {
                prefix.append("* ");
            }
            return Stream.of(
                    "All of:",
                    requirements.stream()
                            .flatMap(requirement -> Arrays.stream(requirement.printRequirements(deep + 1)))
                            .toArray(String[]::new)
            ).map(line -> prefix.toString() + line).toArray(String[]::new);
        }

        @Override
        public boolean meets(DndCharacter character) {
            return requirements.stream().allMatch(requirement -> requirement.meets(character));
        }

    }

    final class AbilityRequirement implements MulticlassingRequirement {

        private final Ability ability;
        private final int minimum;

        public AbilityRequirement(Ability ability, int minimum) {
            this.ability = ability;
            this.minimum = minimum;
        }

        @Override
        public String[] printRequirements() {
            return printRequirements(0);
        }

        @Override
        public String[] printRequirements(int deep) {
            StringBuilder prefix = new StringBuilder(" ");
            for (int i = 0; i < deep; i++) {
                prefix.append("* ");
            }
            return new String[] { prefix.append(ability.getName()).append(": ").append(minimum).toString() };
        }

        @Override
        public boolean meets(DndCharacter character) {
            return character.getAbilityScore(ability) >= minimum;
        }

    }

    boolean meets(DndCharacter character);
    String[] printRequirements();
    String[] printRequirements(int deep);

}
