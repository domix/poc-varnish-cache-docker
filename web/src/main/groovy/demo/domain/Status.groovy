package demo.domain

enum Status {
  GOOD('good', 'foo')
  String name
  String icon
  Status(String name, String icon) {
    this.name = name
    this.icon = icon
  }
}