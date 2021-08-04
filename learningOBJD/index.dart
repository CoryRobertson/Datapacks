import 'package:objd/core.dart';

main(List<String> args) {
  createProject(
      Project(
          name: "learningOBJD",
          target: "./",
          generate: Pack(name: "learningobjd")),
      args);
}
