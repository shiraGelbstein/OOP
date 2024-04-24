public class RendererFactory {
    public RendererFactory(){}
    public Renderer buildRenderer(String type, int size) {
        return switch (type) {
            case ("none") -> new VoidRenderer();
            case ("console") -> new ConsoleRenderer(size);
            default -> null;
        };
    }
}