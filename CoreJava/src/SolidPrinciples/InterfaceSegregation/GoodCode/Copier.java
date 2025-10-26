package SolidPrinciples.InterfaceSegregation.GoodCode;

import SolidPrinciples.InterfaceSegregation.BadCode.Document;

public interface Copier {
    void copy(Document document);
}
