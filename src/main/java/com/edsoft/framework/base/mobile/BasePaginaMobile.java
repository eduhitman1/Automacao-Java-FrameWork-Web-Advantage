package com.edsoft.framework.base.mobile;

public abstract class BasePaginaMobile extends BaseFuncionalidadeMobile {
	/**
	 * BasePaginaMobile extende o retorno do TPage passa da Class
	 * BaseFuncionalidadeMobile
	 * 
	 * @return Tpage
	 */
	public <TPage extends BasePaginaMobile> TPage As(Class<TPage> pageInstance) {
		try {
			return (TPage) this;
		} catch (Exception e) {
			System.out.println("erro: " + e.getStackTrace());
		}
		return null;
	}
}
