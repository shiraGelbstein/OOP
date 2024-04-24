shiragelbstein, elishadiskind
212926737, 208782656

The "tree" package contains 4 classes representing elements of the game's trees. The classes are: leaf, fruit, trunk, and Flora management. These classes is beeing called at flora manager to create and manage the game's trees who calles the flora and completing the proccess.

Flora class : 

+Class overview
The Flora class serves as the central manager within the package, taking responsibility for the creation and supervision of the game's trees. It offers static method for generating trees randomly within a specified X coordinate range of the game window. This process is influenced by the ground height function and avatar jump detection provided during its intialisiation.

+Design Decision:
The class utilizes the constructors or static creation methods of Trunk, Leaf, and Fruit for creating their instances (dependencies relation) and compiles them into a list that is then returned to the caller. Consequently, there is no need for Composition or Inheritance.
The class maintains no direct dependencies on the Terrain or Avatar classes. Instead, it uses the functional interfaces provided in the constructor:
Function<Float, Float> groundHeightFunc = this::groundHeightAt;
Supplier<Boolean> getDetectAvatarJumps = () -> isAvatarJumped;

+Public Methods:
*public Flora(Function<Float, Float> groundHeightFunc, Supplier<Boolean> getDetectAvatarJumps):
Initializes the class with the provided lambdas

*public ArrayList<GameObject> createInRange(int minX, int maxX):
Generates and returns an ArrayList of flora GameObjects within the specified X coordinate range.
Randomly creates trees(trunks, fruits, and leaves) and their components, influenced by ground height and jump detection. inserting each game object as an indevidual into the list .
Utilizes private methods for readability and to encapsulate specific functionalities.

Leaf class:

+Class overview
The Leaf class is designed to represent a leaf GameObject with distinctive properties and dynamic behaviors. It facilitates the creation and management of leaf instances, incorporating features such as swaying and responding appropriately when the avatar is in a jumping state.

+Design Decision:
The decision to implement the Leaf class without inheritance from GameObject is deliberate, guided by the absence of a need to override standard methods like onCollision and update. Instead, the class leverages the addComponent method, offering a more flexible and modular approach. By doing so we 
avoidance of unnecessary overrides and simplified maintenance- thanks to the use of functional interface.

+Public Methods:
*public static GameObject create(Supplier<Boolean> Detector, Vector2 topLeftCorner):
Creates and returns a new leaf GameObject with random scheduled transitions for the leaves swaying,
And one additinal transition that Takes a jump detection supplier and apply transition when ever the 
detecter is true. jump detection transition is using the "addComponent" bulidin method in the dano lab that being called each deltatime.


