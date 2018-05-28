@RestController
class ThisWillActuallyRun {
  @RequestMapping("/")
  String home() {
    return "Hello from Groovy!\n"
  }
}
