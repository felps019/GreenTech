export function Login() {
	return (
		<div>
			<h1>Bem vindo(a) de volta!</h1>
			<div>
				<p>Não possui uma conta?</p>
				<button type="button">Registre-se</button>
			</div>
			<form>
				<div>
					<label htmlFor="e-mail">Email</label>
					<input type="email" />
				</div>

				<div>
					<label htmlFor="password">Senha</label>
					<input type="password" />
				</div>

				<div>
					<label htmlFor="connected">Manter conectado</label>
					<input type="radio" />

					<button type="button">Esqueci a senha</button>
				</div>

				<button type="submit">Fazer login</button>
				{/* Ou opção para entrar com sua conta Google */}
			</form>
		</div>
	);
}
