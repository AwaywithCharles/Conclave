// ICommand.h
#include "document.h"

class ICommand {
public:
    virtual ~ICommand() = default;
    virtual void undo() = 0;
    virtual void redo() = 0;
};

// AppendCommand.h
#include "ICommand.h"

class AppendCommand : public ICommand {
public:
    AppendCommand(Document& doc, const std::string& text);
    void undo() override;
    void redo() override;

private:
    Document& doc;
    std::string text;
};

// AppendCommand.cpp
#include "AppendCommand.h"

AppendCommand::AppendCommand(Document& doc, const std::string& text) : doc(doc), text(text) {
    doc.append(text);
}

void AppendCommand::undo() {
    doc.remove_last();
}

void AppendCommand::redo() {
    doc.append(text);
}

// EditCommand.h
#include "ICommand.h"

class EditCommand : public ICommand {
public:
    EditCommand(Document& doc, int line, const std::string& text);
    void undo() override;
    void redo() override;

private:
    Document& doc;
    int line;
    std::string text;
    std::string old_text;
};

// EditCommand.cpp
#include "EditCommand.h"

EditCommand::EditCommand(Document& doc, int line, const std::string& text) : doc(doc), line(line), text(text) {
    old_text = doc.get_line(line);
    doc.edit(line, text);
}

void EditCommand::undo() {
    doc.edit(line, old_text);
}

void EditCommand::redo() {
    doc.edit(line, text);
}
