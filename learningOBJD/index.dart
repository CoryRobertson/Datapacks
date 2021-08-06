import 'package:objd/core.dart';

// main(List<String> args) {
//   createProject(
//       Project(
//           name: "learningOBJD",
//           target: "./",
//           generate: Pack(name: "learningobjd")),
//       args);
// }
void main(List<String> args) {
  createProject(
    Project(
      name: "This is going to be the generated folder name",
      target: "./", // path for where to generate the project
      generate: CustomWidget(), // The starting point of generation
    ),
    args,
  );
}

class CustomWidget extends Widget {
  @override
  Widget generate(Context context) {
    return Pack(
        name: "mypack",
        main: File(
            // optional
            'main',
            //child: Log("Hello World!")
            child: For.of([
              Command('setblock 0 0 0 obsidian'),
            ])
            //
            ));
  }
}
