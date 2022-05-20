package com.edsoft.framework.base.web;

public abstract class BasePaginaWeb extends BaseFuncionalidadeWeb {

	/**
     * BasePaginaWeb extende o retorno do TPage passa da Class BaseFuncionalidadeWeb
     * @return Tpage
     */
	public <TPage extends BasePaginaWeb> TPage As(Class<TPage> pageInstance) {
		try {
			return (TPage) this;
		} catch (Exception e) {
			System.out.println("erro: " + e.getStackTrace());
		}
		return null;
	}
}
